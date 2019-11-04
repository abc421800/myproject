package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CostUnitProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CostUnitProjectExample() {
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

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Double value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Double value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Double value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Double value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Double value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Double value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Double> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Double> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Double value1, Double value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Double value1, Double value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostIsNull() {
            addCriterion("sub_project_cost is null");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostIsNotNull() {
            addCriterion("sub_project_cost is not null");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostEqualTo(BigDecimal value) {
            addCriterion("sub_project_cost =", value, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostNotEqualTo(BigDecimal value) {
            addCriterion("sub_project_cost <>", value, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostGreaterThan(BigDecimal value) {
            addCriterion("sub_project_cost >", value, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sub_project_cost >=", value, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostLessThan(BigDecimal value) {
            addCriterion("sub_project_cost <", value, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sub_project_cost <=", value, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostIn(List<BigDecimal> values) {
            addCriterion("sub_project_cost in", values, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostNotIn(List<BigDecimal> values) {
            addCriterion("sub_project_cost not in", values, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sub_project_cost between", value1, value2, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andSubProjectCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sub_project_cost not between", value1, value2, "subProjectCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostIsNull() {
            addCriterion("step_item_cost is null");
            return (Criteria) this;
        }

        public Criteria andStepItemCostIsNotNull() {
            addCriterion("step_item_cost is not null");
            return (Criteria) this;
        }

        public Criteria andStepItemCostEqualTo(BigDecimal value) {
            addCriterion("step_item_cost =", value, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostNotEqualTo(BigDecimal value) {
            addCriterion("step_item_cost <>", value, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostGreaterThan(BigDecimal value) {
            addCriterion("step_item_cost >", value, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("step_item_cost >=", value, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostLessThan(BigDecimal value) {
            addCriterion("step_item_cost <", value, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("step_item_cost <=", value, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostIn(List<BigDecimal> values) {
            addCriterion("step_item_cost in", values, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostNotIn(List<BigDecimal> values) {
            addCriterion("step_item_cost not in", values, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("step_item_cost between", value1, value2, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andStepItemCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("step_item_cost not between", value1, value2, "stepItemCost");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeIsNull() {
            addCriterion("other_project_fee is null");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeIsNotNull() {
            addCriterion("other_project_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeEqualTo(Long value) {
            addCriterion("other_project_fee =", value, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeNotEqualTo(Long value) {
            addCriterion("other_project_fee <>", value, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeGreaterThan(Long value) {
            addCriterion("other_project_fee >", value, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("other_project_fee >=", value, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeLessThan(Long value) {
            addCriterion("other_project_fee <", value, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeLessThanOrEqualTo(Long value) {
            addCriterion("other_project_fee <=", value, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeIn(List<Long> values) {
            addCriterion("other_project_fee in", values, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeNotIn(List<Long> values) {
            addCriterion("other_project_fee not in", values, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeBetween(Long value1, Long value2) {
            addCriterion("other_project_fee between", value1, value2, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andOtherProjectFeeNotBetween(Long value1, Long value2) {
            addCriterion("other_project_fee not between", value1, value2, "otherProjectFee");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesIsNull() {
            addCriterion("fees_taxes is null");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesIsNotNull() {
            addCriterion("fees_taxes is not null");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesEqualTo(Long value) {
            addCriterion("fees_taxes =", value, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesNotEqualTo(Long value) {
            addCriterion("fees_taxes <>", value, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesGreaterThan(Long value) {
            addCriterion("fees_taxes >", value, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesGreaterThanOrEqualTo(Long value) {
            addCriterion("fees_taxes >=", value, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesLessThan(Long value) {
            addCriterion("fees_taxes <", value, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesLessThanOrEqualTo(Long value) {
            addCriterion("fees_taxes <=", value, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesIn(List<Long> values) {
            addCriterion("fees_taxes in", values, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesNotIn(List<Long> values) {
            addCriterion("fees_taxes not in", values, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesBetween(Long value1, Long value2) {
            addCriterion("fees_taxes between", value1, value2, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andFeesTaxesNotBetween(Long value1, Long value2) {
            addCriterion("fees_taxes not between", value1, value2, "feesTaxes");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Long value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Long value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Long value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Long value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Long value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Long value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Long> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Long> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Long value1, Long value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Long value1, Long value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenIsNull() {
            addCriterion("unit_project_percen is null");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenIsNotNull() {
            addCriterion("unit_project_percen is not null");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenEqualTo(String value) {
            addCriterion("unit_project_percen =", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenNotEqualTo(String value) {
            addCriterion("unit_project_percen <>", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenGreaterThan(String value) {
            addCriterion("unit_project_percen >", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenGreaterThanOrEqualTo(String value) {
            addCriterion("unit_project_percen >=", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenLessThan(String value) {
            addCriterion("unit_project_percen <", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenLessThanOrEqualTo(String value) {
            addCriterion("unit_project_percen <=", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenLike(String value) {
            addCriterion("unit_project_percen like", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenNotLike(String value) {
            addCriterion("unit_project_percen not like", value, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenIn(List<String> values) {
            addCriterion("unit_project_percen in", values, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenNotIn(List<String> values) {
            addCriterion("unit_project_percen not in", values, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenBetween(String value1, String value2) {
            addCriterion("unit_project_percen between", value1, value2, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andUnitProjectPercenNotBetween(String value1, String value2) {
            addCriterion("unit_project_percen not between", value1, value2, "unitProjectPercen");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaIsNull() {
            addCriterion("covered_area is null");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaIsNotNull() {
            addCriterion("covered_area is not null");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaEqualTo(String value) {
            addCriterion("covered_area =", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaNotEqualTo(String value) {
            addCriterion("covered_area <>", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaGreaterThan(String value) {
            addCriterion("covered_area >", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaGreaterThanOrEqualTo(String value) {
            addCriterion("covered_area >=", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaLessThan(String value) {
            addCriterion("covered_area <", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaLessThanOrEqualTo(String value) {
            addCriterion("covered_area <=", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaLike(String value) {
            addCriterion("covered_area like", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaNotLike(String value) {
            addCriterion("covered_area not like", value, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaIn(List<String> values) {
            addCriterion("covered_area in", values, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaNotIn(List<String> values) {
            addCriterion("covered_area not in", values, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaBetween(String value1, String value2) {
            addCriterion("covered_area between", value1, value2, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andCoveredAreaNotBetween(String value1, String value2) {
            addCriterion("covered_area not between", value1, value2, "coveredArea");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsIsNull() {
            addCriterion("unilateral_indicators is null");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsIsNotNull() {
            addCriterion("unilateral_indicators is not null");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsEqualTo(String value) {
            addCriterion("unilateral_indicators =", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsNotEqualTo(String value) {
            addCriterion("unilateral_indicators <>", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsGreaterThan(String value) {
            addCriterion("unilateral_indicators >", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsGreaterThanOrEqualTo(String value) {
            addCriterion("unilateral_indicators >=", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsLessThan(String value) {
            addCriterion("unilateral_indicators <", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsLessThanOrEqualTo(String value) {
            addCriterion("unilateral_indicators <=", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsLike(String value) {
            addCriterion("unilateral_indicators like", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsNotLike(String value) {
            addCriterion("unilateral_indicators not like", value, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsIn(List<String> values) {
            addCriterion("unilateral_indicators in", values, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsNotIn(List<String> values) {
            addCriterion("unilateral_indicators not in", values, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsBetween(String value1, String value2) {
            addCriterion("unilateral_indicators between", value1, value2, "unilateralIndicators");
            return (Criteria) this;
        }

        public Criteria andUnilateralIndicatorsNotBetween(String value1, String value2) {
            addCriterion("unilateral_indicators not between", value1, value2, "unilateralIndicators");
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