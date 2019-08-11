package com.demo.apidemo.repository;

import com.demo.apidemo.dto.MaxAverageResult;
import com.demo.apidemo.dto.MinMaxResult;
import com.demo.apidemo.dto.TotalAmountByYear;
import com.demo.apidemo.dto.TotalByYearResult;
import com.demo.apidemo.entity.QInstitute;
import com.demo.apidemo.entity.QInvestment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class QueryDslRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public QueryDslRepository(JPAQueryFactory jpaQueryFactory) {
        super(TotalByYearResult.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<TotalByYearResult> getTotalByYear() {
        QInvestment qInvestment = QInvestment.investment;
        return jpaQueryFactory
                .from(qInvestment)
                .groupBy(qInvestment.year,
                        qInvestment.institute.instituteName)
                .select(Projections.fields(TotalByYearResult.class,
                        qInvestment.money.sum().as("averageAmount"),
                        qInvestment.year.as("year"),
                        qInvestment.institute.instituteName.as("instituteName")))
                .fetch();
    }

    public List<TotalAmountByYear> getTotalAmountByYear() {
        QInvestment qInvestment = QInvestment.investment;
        return jpaQueryFactory
                .from(qInvestment)
                .groupBy(qInvestment.year)
                .select(Projections.fields(TotalAmountByYear.class,
                        qInvestment.year.as("year"),
                        qInvestment.money.sum().as("totalAmount")))
                .fetch();
    }

    public MaxAverageResult getMaxAverageYear() {
        QInvestment qInvestment = QInvestment.investment;
        QInstitute qInstitute = QInstitute.institute;
        //NumberPath<Integer> aliasViews = Expressions.numberPath(Integer.class, "average");
        return jpaQueryFactory
                .from(qInvestment)
                .join(qInstitute)
                .on(qInvestment.institute.instituteCode.eq(qInstitute.instituteCode))
                .groupBy(qInvestment.institute.instituteName,
                        qInvestment.year)
                .select(Projections.fields(MaxAverageResult.class,
                        qInvestment.money.avg().intValue().as("average"),
                        qInvestment.institute.instituteName.as("bank"),
                        qInvestment.year))
                .fetch()
                .stream().max(Comparator.comparing(MaxAverageResult::getAverage)).get();
    }

    public List<MinMaxResult> getAverageMinMax(String bankName) {
        QInvestment qInvestment = QInvestment.investment;
        QInstitute qInstitute = QInstitute.institute;
        List<MinMaxResult> queryResult = jpaQueryFactory
                .from(qInvestment)
                .join(qInstitute)
                .on(qInvestment.institute.instituteCode.eq(qInstitute.instituteCode))
                .groupBy(qInvestment.institute.instituteName, qInvestment.year)
                .having(qInvestment.institute.instituteName.eq(bankName))
                .select(Projections.fields(MinMaxResult.class,
                        qInvestment.money.avg().intValue().as("amount"),
                        qInvestment.institute.instituteName.as("bank"),
                        qInvestment.year))
                .fetch();
        List<MinMaxResult> resultList = new ArrayList<>();
        resultList.add(queryResult.stream().max(Comparator.comparing(MinMaxResult::getAmount)).get());
        resultList.add(queryResult.stream().min(Comparator.comparing(MinMaxResult::getAmount)).get());

        return resultList;
    }
}
