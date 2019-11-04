package com.cost168.costaudit.pojo.cost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostDocumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CostDocumentExample() {
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

        public Criteria andSymbolIsNull() {
            addCriterion("symbol is null");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNotNull() {
            addCriterion("symbol is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolEqualTo(String value) {
            addCriterion("symbol =", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotEqualTo(String value) {
            addCriterion("symbol <>", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThan(String value) {
            addCriterion("symbol >", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThanOrEqualTo(String value) {
            addCriterion("symbol >=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThan(String value) {
            addCriterion("symbol <", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThanOrEqualTo(String value) {
            addCriterion("symbol <=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLike(String value) {
            addCriterion("symbol like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotLike(String value) {
            addCriterion("symbol not like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolIn(List<String> values) {
            addCriterion("symbol in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotIn(List<String> values) {
            addCriterion("symbol not in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolBetween(String value1, String value2) {
            addCriterion("symbol between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotBetween(String value1, String value2) {
            addCriterion("symbol not between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagIsNull() {
            addCriterion("come_go_flag is null");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagIsNotNull() {
            addCriterion("come_go_flag is not null");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagEqualTo(String value) {
            addCriterion("come_go_flag =", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagNotEqualTo(String value) {
            addCriterion("come_go_flag <>", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagGreaterThan(String value) {
            addCriterion("come_go_flag >", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagGreaterThanOrEqualTo(String value) {
            addCriterion("come_go_flag >=", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagLessThan(String value) {
            addCriterion("come_go_flag <", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagLessThanOrEqualTo(String value) {
            addCriterion("come_go_flag <=", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagLike(String value) {
            addCriterion("come_go_flag like", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagNotLike(String value) {
            addCriterion("come_go_flag not like", value, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagIn(List<String> values) {
            addCriterion("come_go_flag in", values, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagNotIn(List<String> values) {
            addCriterion("come_go_flag not in", values, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagBetween(String value1, String value2) {
            addCriterion("come_go_flag between", value1, value2, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoFlagNotBetween(String value1, String value2) {
            addCriterion("come_go_flag not between", value1, value2, "comeGoFlag");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitIsNull() {
            addCriterion("come_go_unit is null");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitIsNotNull() {
            addCriterion("come_go_unit is not null");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitEqualTo(String value) {
            addCriterion("come_go_unit =", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitNotEqualTo(String value) {
            addCriterion("come_go_unit <>", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitGreaterThan(String value) {
            addCriterion("come_go_unit >", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitGreaterThanOrEqualTo(String value) {
            addCriterion("come_go_unit >=", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitLessThan(String value) {
            addCriterion("come_go_unit <", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitLessThanOrEqualTo(String value) {
            addCriterion("come_go_unit <=", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitLike(String value) {
            addCriterion("come_go_unit like", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitNotLike(String value) {
            addCriterion("come_go_unit not like", value, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitIn(List<String> values) {
            addCriterion("come_go_unit in", values, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitNotIn(List<String> values) {
            addCriterion("come_go_unit not in", values, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitBetween(String value1, String value2) {
            addCriterion("come_go_unit between", value1, value2, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andComeGoUnitNotBetween(String value1, String value2) {
            addCriterion("come_go_unit not between", value1, value2, "comeGoUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagIsNull() {
            addCriterion("audit_price_flag is null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagIsNotNull() {
            addCriterion("audit_price_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagEqualTo(String value) {
            addCriterion("audit_price_flag =", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagNotEqualTo(String value) {
            addCriterion("audit_price_flag <>", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagGreaterThan(String value) {
            addCriterion("audit_price_flag >", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("audit_price_flag >=", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagLessThan(String value) {
            addCriterion("audit_price_flag <", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagLessThanOrEqualTo(String value) {
            addCriterion("audit_price_flag <=", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagLike(String value) {
            addCriterion("audit_price_flag like", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagNotLike(String value) {
            addCriterion("audit_price_flag not like", value, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagIn(List<String> values) {
            addCriterion("audit_price_flag in", values, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagNotIn(List<String> values) {
            addCriterion("audit_price_flag not in", values, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagBetween(String value1, String value2) {
            addCriterion("audit_price_flag between", value1, value2, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andAuditPriceFlagNotBetween(String value1, String value2) {
            addCriterion("audit_price_flag not between", value1, value2, "auditPriceFlag");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeIsNull() {
            addCriterion("document_time is null");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeIsNotNull() {
            addCriterion("document_time is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeEqualTo(Date value) {
            addCriterion("document_time =", value, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeNotEqualTo(Date value) {
            addCriterion("document_time <>", value, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeGreaterThan(Date value) {
            addCriterion("document_time >", value, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("document_time >=", value, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeLessThan(Date value) {
            addCriterion("document_time <", value, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeLessThanOrEqualTo(Date value) {
            addCriterion("document_time <=", value, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeIn(List<Date> values) {
            addCriterion("document_time in", values, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeNotIn(List<Date> values) {
            addCriterion("document_time not in", values, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeBetween(Date value1, Date value2) {
            addCriterion("document_time between", value1, value2, "documentTime");
            return (Criteria) this;
        }

        public Criteria andDocumentTimeNotBetween(Date value1, Date value2) {
            addCriterion("document_time not between", value1, value2, "documentTime");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIsNull() {
            addCriterion("person_liable is null");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIsNotNull() {
            addCriterion("person_liable is not null");
            return (Criteria) this;
        }

        public Criteria andPersonLiableEqualTo(String value) {
            addCriterion("person_liable =", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableNotEqualTo(String value) {
            addCriterion("person_liable <>", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableGreaterThan(String value) {
            addCriterion("person_liable >", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableGreaterThanOrEqualTo(String value) {
            addCriterion("person_liable >=", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableLessThan(String value) {
            addCriterion("person_liable <", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableLessThanOrEqualTo(String value) {
            addCriterion("person_liable <=", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableLike(String value) {
            addCriterion("person_liable like", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableNotLike(String value) {
            addCriterion("person_liable not like", value, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIn(List<String> values) {
            addCriterion("person_liable in", values, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableNotIn(List<String> values) {
            addCriterion("person_liable not in", values, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableBetween(String value1, String value2) {
            addCriterion("person_liable between", value1, value2, "personLiable");
            return (Criteria) this;
        }

        public Criteria andPersonLiableNotBetween(String value1, String value2) {
            addCriterion("person_liable not between", value1, value2, "personLiable");
            return (Criteria) this;
        }

        public Criteria andRegistrantIsNull() {
            addCriterion("registrant is null");
            return (Criteria) this;
        }

        public Criteria andRegistrantIsNotNull() {
            addCriterion("registrant is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrantEqualTo(String value) {
            addCriterion("registrant =", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantNotEqualTo(String value) {
            addCriterion("registrant <>", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantGreaterThan(String value) {
            addCriterion("registrant >", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantGreaterThanOrEqualTo(String value) {
            addCriterion("registrant >=", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantLessThan(String value) {
            addCriterion("registrant <", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantLessThanOrEqualTo(String value) {
            addCriterion("registrant <=", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantLike(String value) {
            addCriterion("registrant like", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantNotLike(String value) {
            addCriterion("registrant not like", value, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantIn(List<String> values) {
            addCriterion("registrant in", values, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantNotIn(List<String> values) {
            addCriterion("registrant not in", values, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantBetween(String value1, String value2) {
            addCriterion("registrant between", value1, value2, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantNotBetween(String value1, String value2) {
            addCriterion("registrant not between", value1, value2, "registrant");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeIsNull() {
            addCriterion("registrant_time is null");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeIsNotNull() {
            addCriterion("registrant_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeEqualTo(Date value) {
            addCriterion("registrant_time =", value, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeNotEqualTo(Date value) {
            addCriterion("registrant_time <>", value, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeGreaterThan(Date value) {
            addCriterion("registrant_time >", value, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("registrant_time >=", value, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeLessThan(Date value) {
            addCriterion("registrant_time <", value, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeLessThanOrEqualTo(Date value) {
            addCriterion("registrant_time <=", value, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeIn(List<Date> values) {
            addCriterion("registrant_time in", values, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeNotIn(List<Date> values) {
            addCriterion("registrant_time not in", values, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeBetween(Date value1, Date value2) {
            addCriterion("registrant_time between", value1, value2, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andRegistrantTimeNotBetween(Date value1, Date value2) {
            addCriterion("registrant_time not between", value1, value2, "registrantTime");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andOpinionIsNull() {
            addCriterion("opinion is null");
            return (Criteria) this;
        }

        public Criteria andOpinionIsNotNull() {
            addCriterion("opinion is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionEqualTo(String value) {
            addCriterion("opinion =", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotEqualTo(String value) {
            addCriterion("opinion <>", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionGreaterThan(String value) {
            addCriterion("opinion >", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("opinion >=", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLessThan(String value) {
            addCriterion("opinion <", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLessThanOrEqualTo(String value) {
            addCriterion("opinion <=", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLike(String value) {
            addCriterion("opinion like", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotLike(String value) {
            addCriterion("opinion not like", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionIn(List<String> values) {
            addCriterion("opinion in", values, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotIn(List<String> values) {
            addCriterion("opinion not in", values, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionBetween(String value1, String value2) {
            addCriterion("opinion between", value1, value2, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotBetween(String value1, String value2) {
            addCriterion("opinion not between", value1, value2, "opinion");
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