package com.demo.apidemo.repository;

import com.demo.apidemo.dto.MaxAverageResult;
import com.demo.apidemo.dto.MinMaxResult;
import com.demo.apidemo.dto.TotalAmountByYear;
import com.demo.apidemo.dto.TotalByYearResult;
import com.demo.apidemo.entity.QInstitute;
import com.demo.apidemo.entity.QSupportedAmount;
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
        QSupportedAmount qSupportedAmount = QSupportedAmount.supportedAmount;
        return jpaQueryFactory
                .from(qSupportedAmount)
                .groupBy(qSupportedAmount.year,
                        qSupportedAmount.institute.instituteName)
                .select(Projections.fields(TotalByYearResult.class,
                        qSupportedAmount.money.sum().as("averageAmount"),
                        qSupportedAmount.year.as("year"),
                        qSupportedAmount.institute.instituteName.as("instituteName")))
                .fetch();
    }

    public List<TotalAmountByYear> getTotalAmountByYear() {
        QSupportedAmount qSupportedAmount = QSupportedAmount.supportedAmount;
        return jpaQueryFactory
                .from(qSupportedAmount)
                .groupBy(qSupportedAmount.year)
                .select(Projections.fields(TotalAmountByYear.class,
                        qSupportedAmount.year.as("year"),
                        qSupportedAmount.money.sum().as("totalAmount")))
                .fetch();
    }

    public MaxAverageResult getMaxAverageYear() {
        QSupportedAmount qSupportedAmount = QSupportedAmount.supportedAmount;
        QInstitute qInstitute = QInstitute.institute;
        //NumberPath<Integer> aliasViews = Expressions.numberPath(Integer.class, "average");
        return jpaQueryFactory
                .from(qSupportedAmount)
                .join(qInstitute)
                .on(qSupportedAmount.institute.instituteCode.eq(qInstitute.instituteCode))
                .groupBy(qSupportedAmount.institute.instituteName,
                        qSupportedAmount.year)
                .select(Projections.fields(MaxAverageResult.class,
                        qSupportedAmount.money.avg().intValue().as("average"),
                        qSupportedAmount.institute.instituteName.as("bank"),
                        qSupportedAmount.year))
                .fetch()
                .stream().max(Comparator.comparing(MaxAverageResult::getAverage)).get();
    }

    public List<MinMaxResult> getAverageMinMax(String bankName) {
        QSupportedAmount qSupportedAmount = QSupportedAmount.supportedAmount;
        QInstitute qInstitute = QInstitute.institute;
        List<MinMaxResult> queryResult = jpaQueryFactory
                .from(qSupportedAmount)
                .join(qInstitute)
                .on(qSupportedAmount.institute.instituteCode.eq(qInstitute.instituteCode))
                .groupBy(qSupportedAmount.institute.instituteName, qSupportedAmount.year)
                .having(qSupportedAmount.institute.instituteName.eq(bankName))
                .select(Projections.fields(MinMaxResult.class,
                        qSupportedAmount.money.avg().intValue().as("amount"),
                        qSupportedAmount.institute.instituteName.as("bank"),
                        qSupportedAmount.year))
                .fetch();
        List<MinMaxResult> resultList = new ArrayList<>();
        resultList.add(queryResult.stream().max(Comparator.comparing(MinMaxResult::getAmount)).get());
        resultList.add(queryResult.stream().min(Comparator.comparing(MinMaxResult::getAmount)).get());

        return resultList;
    }
}
