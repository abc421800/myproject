package com.cost168.costaudit.pojo.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkRegisterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkRegisterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPersonIdIsNull() {
            addCriterion("person_id is null");
            return (Criteria) this;
        }

        public Criteria andPersonIdIsNotNull() {
            addCriterion("person_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersonIdEqualTo(String value) {
            addCriterion("person_id =", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotEqualTo(String value) {
            addCriterion("person_id <>", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdGreaterThan(String value) {
            addCriterion("person_id >", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdGreaterThanOrEqualTo(String value) {
            addCriterion("person_id >=", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLessThan(String value) {
            addCriterion("person_id <", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLessThanOrEqualTo(String value) {
            addCriterion("person_id <=", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLike(String value) {
            addCriterion("person_id like", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotLike(String value) {
            addCriterion("person_id not like", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdIn(List<String> values) {
            addCriterion("person_id in", values, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotIn(List<String> values) {
            addCriterion("person_id not in", values, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdBetween(String value1, String value2) {
            addCriterion("person_id between", value1, value2, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotBetween(String value1, String value2) {
            addCriterion("person_id not between", value1, value2, "personId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdIsNull() {
            addCriterion("enterprise_id is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdIsNotNull() {
            addCriterion("enterprise_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdEqualTo(String value) {
            addCriterion("enterprise_id =", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotEqualTo(String value) {
            addCriterion("enterprise_id <>", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdGreaterThan(String value) {
            addCriterion("enterprise_id >", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_id >=", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdLessThan(String value) {
            addCriterion("enterprise_id <", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdLessThanOrEqualTo(String value) {
            addCriterion("enterprise_id <=", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdLike(String value) {
            addCriterion("enterprise_id like", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotLike(String value) {
            addCriterion("enterprise_id not like", value, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdIn(List<String> values) {
            addCriterion("enterprise_id in", values, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotIn(List<String> values) {
            addCriterion("enterprise_id not in", values, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdBetween(String value1, String value2) {
            addCriterion("enterprise_id between", value1, value2, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andEnterpriseIdNotBetween(String value1, String value2) {
            addCriterion("enterprise_id not between", value1, value2, "enterpriseId");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthIsNull() {
            addCriterion("reg_year_month is null");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthIsNotNull() {
            addCriterion("reg_year_month is not null");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthEqualTo(String value) {
            addCriterion("reg_year_month =", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthNotEqualTo(String value) {
            addCriterion("reg_year_month <>", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthGreaterThan(String value) {
            addCriterion("reg_year_month >", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthGreaterThanOrEqualTo(String value) {
            addCriterion("reg_year_month >=", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthLessThan(String value) {
            addCriterion("reg_year_month <", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthLessThanOrEqualTo(String value) {
            addCriterion("reg_year_month <=", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthLike(String value) {
            addCriterion("reg_year_month like", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthNotLike(String value) {
            addCriterion("reg_year_month not like", value, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthIn(List<String> values) {
            addCriterion("reg_year_month in", values, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthNotIn(List<String> values) {
            addCriterion("reg_year_month not in", values, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthBetween(String value1, String value2) {
            addCriterion("reg_year_month between", value1, value2, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearMonthNotBetween(String value1, String value2) {
            addCriterion("reg_year_month not between", value1, value2, "regYearMonth");
            return (Criteria) this;
        }

        public Criteria andRegYearIsNull() {
            addCriterion("reg_year is null");
            return (Criteria) this;
        }

        public Criteria andRegYearIsNotNull() {
            addCriterion("reg_year is not null");
            return (Criteria) this;
        }

        public Criteria andRegYearEqualTo(String value) {
            addCriterion("reg_year =", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearNotEqualTo(String value) {
            addCriterion("reg_year <>", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearGreaterThan(String value) {
            addCriterion("reg_year >", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearGreaterThanOrEqualTo(String value) {
            addCriterion("reg_year >=", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearLessThan(String value) {
            addCriterion("reg_year <", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearLessThanOrEqualTo(String value) {
            addCriterion("reg_year <=", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearLike(String value) {
            addCriterion("reg_year like", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearNotLike(String value) {
            addCriterion("reg_year not like", value, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearIn(List<String> values) {
            addCriterion("reg_year in", values, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearNotIn(List<String> values) {
            addCriterion("reg_year not in", values, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearBetween(String value1, String value2) {
            addCriterion("reg_year between", value1, value2, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegYearNotBetween(String value1, String value2) {
            addCriterion("reg_year not between", value1, value2, "regYear");
            return (Criteria) this;
        }

        public Criteria andRegMonthIsNull() {
            addCriterion("reg_month is null");
            return (Criteria) this;
        }

        public Criteria andRegMonthIsNotNull() {
            addCriterion("reg_month is not null");
            return (Criteria) this;
        }

        public Criteria andRegMonthEqualTo(String value) {
            addCriterion("reg_month =", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthNotEqualTo(String value) {
            addCriterion("reg_month <>", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthGreaterThan(String value) {
            addCriterion("reg_month >", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthGreaterThanOrEqualTo(String value) {
            addCriterion("reg_month >=", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthLessThan(String value) {
            addCriterion("reg_month <", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthLessThanOrEqualTo(String value) {
            addCriterion("reg_month <=", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthLike(String value) {
            addCriterion("reg_month like", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthNotLike(String value) {
            addCriterion("reg_month not like", value, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthIn(List<String> values) {
            addCriterion("reg_month in", values, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthNotIn(List<String> values) {
            addCriterion("reg_month not in", values, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthBetween(String value1, String value2) {
            addCriterion("reg_month between", value1, value2, "regMonth");
            return (Criteria) this;
        }

        public Criteria andRegMonthNotBetween(String value1, String value2) {
            addCriterion("reg_month not between", value1, value2, "regMonth");
            return (Criteria) this;
        }

        public Criteria andMorningIsNull() {
            addCriterion("morning is null");
            return (Criteria) this;
        }

        public Criteria andMorningIsNotNull() {
            addCriterion("morning is not null");
            return (Criteria) this;
        }

        public Criteria andMorningEqualTo(String value) {
            addCriterion("morning =", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotEqualTo(String value) {
            addCriterion("morning <>", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningGreaterThan(String value) {
            addCriterion("morning >", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningGreaterThanOrEqualTo(String value) {
            addCriterion("morning >=", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningLessThan(String value) {
            addCriterion("morning <", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningLessThanOrEqualTo(String value) {
            addCriterion("morning <=", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningLike(String value) {
            addCriterion("morning like", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotLike(String value) {
            addCriterion("morning not like", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningIn(List<String> values) {
            addCriterion("morning in", values, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotIn(List<String> values) {
            addCriterion("morning not in", values, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningBetween(String value1, String value2) {
            addCriterion("morning between", value1, value2, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotBetween(String value1, String value2) {
            addCriterion("morning not between", value1, value2, "morning");
            return (Criteria) this;
        }

        public Criteria andAfternoonIsNull() {
            addCriterion("afternoon is null");
            return (Criteria) this;
        }

        public Criteria andAfternoonIsNotNull() {
            addCriterion("afternoon is not null");
            return (Criteria) this;
        }

        public Criteria andAfternoonEqualTo(String value) {
            addCriterion("afternoon =", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotEqualTo(String value) {
            addCriterion("afternoon <>", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonGreaterThan(String value) {
            addCriterion("afternoon >", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonGreaterThanOrEqualTo(String value) {
            addCriterion("afternoon >=", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonLessThan(String value) {
            addCriterion("afternoon <", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonLessThanOrEqualTo(String value) {
            addCriterion("afternoon <=", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonLike(String value) {
            addCriterion("afternoon like", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotLike(String value) {
            addCriterion("afternoon not like", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonIn(List<String> values) {
            addCriterion("afternoon in", values, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotIn(List<String> values) {
            addCriterion("afternoon not in", values, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonBetween(String value1, String value2) {
            addCriterion("afternoon between", value1, value2, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotBetween(String value1, String value2) {
            addCriterion("afternoon not between", value1, value2, "afternoon");
            return (Criteria) this;
        }

        public Criteria andRegDayIsNull() {
            addCriterion("reg_day is null");
            return (Criteria) this;
        }

        public Criteria andRegDayIsNotNull() {
            addCriterion("reg_day is not null");
            return (Criteria) this;
        }

        public Criteria andRegDayEqualTo(String value) {
            addCriterion("reg_day =", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayNotEqualTo(String value) {
            addCriterion("reg_day <>", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayGreaterThan(String value) {
            addCriterion("reg_day >", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayGreaterThanOrEqualTo(String value) {
            addCriterion("reg_day >=", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayLessThan(String value) {
            addCriterion("reg_day <", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayLessThanOrEqualTo(String value) {
            addCriterion("reg_day <=", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayLike(String value) {
            addCriterion("reg_day like", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayNotLike(String value) {
            addCriterion("reg_day not like", value, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayIn(List<String> values) {
            addCriterion("reg_day in", values, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayNotIn(List<String> values) {
            addCriterion("reg_day not in", values, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayBetween(String value1, String value2) {
            addCriterion("reg_day between", value1, value2, "regDay");
            return (Criteria) this;
        }

        public Criteria andRegDayNotBetween(String value1, String value2) {
            addCriterion("reg_day not between", value1, value2, "regDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayIsNull() {
            addCriterion("working_day is null");
            return (Criteria) this;
        }

        public Criteria andWorkingDayIsNotNull() {
            addCriterion("working_day is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingDayEqualTo(Float value) {
            addCriterion("working_day =", value, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayNotEqualTo(Float value) {
            addCriterion("working_day <>", value, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayGreaterThan(Float value) {
            addCriterion("working_day >", value, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayGreaterThanOrEqualTo(Float value) {
            addCriterion("working_day >=", value, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayLessThan(Float value) {
            addCriterion("working_day <", value, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayLessThanOrEqualTo(Float value) {
            addCriterion("working_day <=", value, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayIn(List<Float> values) {
            addCriterion("working_day in", values, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayNotIn(List<Float> values) {
            addCriterion("working_day not in", values, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayBetween(Float value1, Float value2) {
            addCriterion("working_day between", value1, value2, "workingDay");
            return (Criteria) this;
        }

        public Criteria andWorkingDayNotBetween(Float value1, Float value2) {
            addCriterion("working_day not between", value1, value2, "workingDay");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNull() {
            addCriterion("register_time is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNotNull() {
            addCriterion("register_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeEqualTo(Date value) {
            addCriterion("register_time =", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotEqualTo(Date value) {
            addCriterion("register_time <>", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThan(Date value) {
            addCriterion("register_time >", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("register_time >=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThan(Date value) {
            addCriterion("register_time <", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThanOrEqualTo(Date value) {
            addCriterion("register_time <=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIn(List<Date> values) {
            addCriterion("register_time in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotIn(List<Date> values) {
            addCriterion("register_time not in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeBetween(Date value1, Date value2) {
            addCriterion("register_time between", value1, value2, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotBetween(Date value1, Date value2) {
            addCriterion("register_time not between", value1, value2, "registerTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}