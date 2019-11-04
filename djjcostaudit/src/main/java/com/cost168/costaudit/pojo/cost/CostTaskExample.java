package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CostTaskExample() {
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

        public Criteria andDocumentIdIsNull() {
            addCriterion("document_id is null");
            return (Criteria) this;
        }

        public Criteria andDocumentIdIsNotNull() {
            addCriterion("document_id is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentIdEqualTo(String value) {
            addCriterion("document_id =", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdNotEqualTo(String value) {
            addCriterion("document_id <>", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdGreaterThan(String value) {
            addCriterion("document_id >", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdGreaterThanOrEqualTo(String value) {
            addCriterion("document_id >=", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdLessThan(String value) {
            addCriterion("document_id <", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdLessThanOrEqualTo(String value) {
            addCriterion("document_id <=", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdLike(String value) {
            addCriterion("document_id like", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdNotLike(String value) {
            addCriterion("document_id not like", value, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdIn(List<String> values) {
            addCriterion("document_id in", values, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdNotIn(List<String> values) {
            addCriterion("document_id not in", values, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdBetween(String value1, String value2) {
            addCriterion("document_id between", value1, value2, "documentId");
            return (Criteria) this;
        }

        public Criteria andDocumentIdNotBetween(String value1, String value2) {
            addCriterion("document_id not between", value1, value2, "documentId");
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNull() {
            addCriterion("contract_id is null");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNotNull() {
            addCriterion("contract_id is not null");
            return (Criteria) this;
        }

        public Criteria andContractIdEqualTo(String value) {
            addCriterion("contract_id =", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotEqualTo(String value) {
            addCriterion("contract_id <>", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThan(String value) {
            addCriterion("contract_id >", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThanOrEqualTo(String value) {
            addCriterion("contract_id >=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThan(String value) {
            addCriterion("contract_id <", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThanOrEqualTo(String value) {
            addCriterion("contract_id <=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLike(String value) {
            addCriterion("contract_id like", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotLike(String value) {
            addCriterion("contract_id not like", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdIn(List<String> values) {
            addCriterion("contract_id in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotIn(List<String> values) {
            addCriterion("contract_id not in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdBetween(String value1, String value2) {
            addCriterion("contract_id between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotBetween(String value1, String value2) {
            addCriterion("contract_id not between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNull() {
            addCriterion("contract_name is null");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNotNull() {
            addCriterion("contract_name is not null");
            return (Criteria) this;
        }

        public Criteria andContractNameEqualTo(String value) {
            addCriterion("contract_name =", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotEqualTo(String value) {
            addCriterion("contract_name <>", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThan(String value) {
            addCriterion("contract_name >", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThanOrEqualTo(String value) {
            addCriterion("contract_name >=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThan(String value) {
            addCriterion("contract_name <", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThanOrEqualTo(String value) {
            addCriterion("contract_name <=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLike(String value) {
            addCriterion("contract_name like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotLike(String value) {
            addCriterion("contract_name not like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameIn(List<String> values) {
            addCriterion("contract_name in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotIn(List<String> values) {
            addCriterion("contract_name not in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameBetween(String value1, String value2) {
            addCriterion("contract_name between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotBetween(String value1, String value2) {
            addCriterion("contract_name not between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andAuditPriceTypeIsNull() {
            addCriterion("audit_price_type is null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeIsNotNull() {
            addCriterion("audit_price_type is not null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeEqualTo(String value) {
            addCriterion("audit_price_type =", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeNotEqualTo(String value) {
            addCriterion("audit_price_type <>", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeGreaterThan(String value) {
            addCriterion("audit_price_type >", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("audit_price_type >=", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeLessThan(String value) {
            addCriterion("audit_price_type <", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeLessThanOrEqualTo(String value) {
            addCriterion("audit_price_type <=", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeLike(String value) {
            addCriterion("audit_price_type like", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeNotLike(String value) {
            addCriterion("audit_price_type not like", value, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeIn(List<String> values) {
            addCriterion("audit_price_type in", values, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeNotIn(List<String> values) {
            addCriterion("audit_price_type not in", values, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeBetween(String value1, String value2) {
            addCriterion("audit_price_type between", value1, value2, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypeNotBetween(String value1, String value2) {
            addCriterion("audit_price_type not between", value1, value2, "auditPriceType");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnIsNull() {
            addCriterion("audit_price_typecn is null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnIsNotNull() {
            addCriterion("audit_price_typecn is not null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnEqualTo(String value) {
            addCriterion("audit_price_typecn =", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnNotEqualTo(String value) {
            addCriterion("audit_price_typecn <>", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnGreaterThan(String value) {
            addCriterion("audit_price_typecn >", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnGreaterThanOrEqualTo(String value) {
            addCriterion("audit_price_typecn >=", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnLessThan(String value) {
            addCriterion("audit_price_typecn <", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnLessThanOrEqualTo(String value) {
            addCriterion("audit_price_typecn <=", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnLike(String value) {
            addCriterion("audit_price_typecn like", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnNotLike(String value) {
            addCriterion("audit_price_typecn not like", value, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnIn(List<String> values) {
            addCriterion("audit_price_typecn in", values, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnNotIn(List<String> values) {
            addCriterion("audit_price_typecn not in", values, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnBetween(String value1, String value2) {
            addCriterion("audit_price_typecn between", value1, value2, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andAuditPriceTypecnNotBetween(String value1, String value2) {
            addCriterion("audit_price_typecn not between", value1, value2, "auditPriceTypecn");
            return (Criteria) this;
        }

        public Criteria andContractAmountIsNull() {
            addCriterion("contract_amount is null");
            return (Criteria) this;
        }

        public Criteria andContractAmountIsNotNull() {
            addCriterion("contract_amount is not null");
            return (Criteria) this;
        }

        public Criteria andContractAmountEqualTo(BigDecimal value) {
            addCriterion("contract_amount =", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountNotEqualTo(BigDecimal value) {
            addCriterion("contract_amount <>", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountGreaterThan(BigDecimal value) {
            addCriterion("contract_amount >", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_amount >=", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountLessThan(BigDecimal value) {
            addCriterion("contract_amount <", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_amount <=", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountIn(List<BigDecimal> values) {
            addCriterion("contract_amount in", values, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountNotIn(List<BigDecimal> values) {
            addCriterion("contract_amount not in", values, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_amount between", value1, value2, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_amount not between", value1, value2, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountIsNull() {
            addCriterion("give_amount is null");
            return (Criteria) this;
        }

        public Criteria andGiveAmountIsNotNull() {
            addCriterion("give_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGiveAmountEqualTo(BigDecimal value) {
            addCriterion("give_amount =", value, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountNotEqualTo(BigDecimal value) {
            addCriterion("give_amount <>", value, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountGreaterThan(BigDecimal value) {
            addCriterion("give_amount >", value, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("give_amount >=", value, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountLessThan(BigDecimal value) {
            addCriterion("give_amount <", value, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("give_amount <=", value, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountIn(List<BigDecimal> values) {
            addCriterion("give_amount in", values, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountNotIn(List<BigDecimal> values) {
            addCriterion("give_amount not in", values, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("give_amount between", value1, value2, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andGiveAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("give_amount not between", value1, value2, "giveAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountIsNull() {
            addCriterion("my_audit_amount is null");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountIsNotNull() {
            addCriterion("my_audit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountEqualTo(BigDecimal value) {
            addCriterion("my_audit_amount =", value, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountNotEqualTo(BigDecimal value) {
            addCriterion("my_audit_amount <>", value, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountGreaterThan(BigDecimal value) {
            addCriterion("my_audit_amount >", value, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("my_audit_amount >=", value, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountLessThan(BigDecimal value) {
            addCriterion("my_audit_amount <", value, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("my_audit_amount <=", value, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountIn(List<BigDecimal> values) {
            addCriterion("my_audit_amount in", values, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountNotIn(List<BigDecimal> values) {
            addCriterion("my_audit_amount not in", values, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("my_audit_amount between", value1, value2, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andMyAuditAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("my_audit_amount not between", value1, value2, "myAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountIsNull() {
            addCriterion("agency_audit_amount is null");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountIsNotNull() {
            addCriterion("agency_audit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountEqualTo(BigDecimal value) {
            addCriterion("agency_audit_amount =", value, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountNotEqualTo(BigDecimal value) {
            addCriterion("agency_audit_amount <>", value, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountGreaterThan(BigDecimal value) {
            addCriterion("agency_audit_amount >", value, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agency_audit_amount >=", value, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountLessThan(BigDecimal value) {
            addCriterion("agency_audit_amount <", value, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agency_audit_amount <=", value, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountIn(List<BigDecimal> values) {
            addCriterion("agency_audit_amount in", values, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountNotIn(List<BigDecimal> values) {
            addCriterion("agency_audit_amount not in", values, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agency_audit_amount between", value1, value2, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andAgencyAuditAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agency_audit_amount not between", value1, value2, "agencyAuditAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountIsNull() {
            addCriterion("decide_amount is null");
            return (Criteria) this;
        }

        public Criteria andDecideAmountIsNotNull() {
            addCriterion("decide_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDecideAmountEqualTo(BigDecimal value) {
            addCriterion("decide_amount =", value, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountNotEqualTo(BigDecimal value) {
            addCriterion("decide_amount <>", value, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountGreaterThan(BigDecimal value) {
            addCriterion("decide_amount >", value, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("decide_amount >=", value, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountLessThan(BigDecimal value) {
            addCriterion("decide_amount <", value, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("decide_amount <=", value, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountIn(List<BigDecimal> values) {
            addCriterion("decide_amount in", values, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountNotIn(List<BigDecimal> values) {
            addCriterion("decide_amount not in", values, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("decide_amount between", value1, value2, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andDecideAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("decide_amount not between", value1, value2, "decideAmount");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberIsNull() {
            addCriterion("finalize_number is null");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberIsNotNull() {
            addCriterion("finalize_number is not null");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberEqualTo(String value) {
            addCriterion("finalize_number =", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberNotEqualTo(String value) {
            addCriterion("finalize_number <>", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberGreaterThan(String value) {
            addCriterion("finalize_number >", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberGreaterThanOrEqualTo(String value) {
            addCriterion("finalize_number >=", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberLessThan(String value) {
            addCriterion("finalize_number <", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberLessThanOrEqualTo(String value) {
            addCriterion("finalize_number <=", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberLike(String value) {
            addCriterion("finalize_number like", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberNotLike(String value) {
            addCriterion("finalize_number not like", value, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberIn(List<String> values) {
            addCriterion("finalize_number in", values, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberNotIn(List<String> values) {
            addCriterion("finalize_number not in", values, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberBetween(String value1, String value2) {
            addCriterion("finalize_number between", value1, value2, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andFinalizeNumberNotBetween(String value1, String value2) {
            addCriterion("finalize_number not between", value1, value2, "finalizeNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagIsNull() {
            addCriterion("delivery_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagIsNotNull() {
            addCriterion("delivery_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagEqualTo(String value) {
            addCriterion("delivery_flag =", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagNotEqualTo(String value) {
            addCriterion("delivery_flag <>", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagGreaterThan(String value) {
            addCriterion("delivery_flag >", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_flag >=", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagLessThan(String value) {
            addCriterion("delivery_flag <", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagLessThanOrEqualTo(String value) {
            addCriterion("delivery_flag <=", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagLike(String value) {
            addCriterion("delivery_flag like", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagNotLike(String value) {
            addCriterion("delivery_flag not like", value, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagIn(List<String> values) {
            addCriterion("delivery_flag in", values, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagNotIn(List<String> values) {
            addCriterion("delivery_flag not in", values, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagBetween(String value1, String value2) {
            addCriterion("delivery_flag between", value1, value2, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andDeliveryFlagNotBetween(String value1, String value2) {
            addCriterion("delivery_flag not between", value1, value2, "deliveryFlag");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberIsNull() {
            addCriterion("entrust_number is null");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberIsNotNull() {
            addCriterion("entrust_number is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberEqualTo(String value) {
            addCriterion("entrust_number =", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberNotEqualTo(String value) {
            addCriterion("entrust_number <>", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberGreaterThan(String value) {
            addCriterion("entrust_number >", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_number >=", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberLessThan(String value) {
            addCriterion("entrust_number <", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberLessThanOrEqualTo(String value) {
            addCriterion("entrust_number <=", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberLike(String value) {
            addCriterion("entrust_number like", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberNotLike(String value) {
            addCriterion("entrust_number not like", value, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberIn(List<String> values) {
            addCriterion("entrust_number in", values, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberNotIn(List<String> values) {
            addCriterion("entrust_number not in", values, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberBetween(String value1, String value2) {
            addCriterion("entrust_number between", value1, value2, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andEntrustNumberNotBetween(String value1, String value2) {
            addCriterion("entrust_number not between", value1, value2, "entrustNumber");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitIsNull() {
            addCriterion("audit_price_unit is null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitIsNotNull() {
            addCriterion("audit_price_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitEqualTo(String value) {
            addCriterion("audit_price_unit =", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitNotEqualTo(String value) {
            addCriterion("audit_price_unit <>", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitGreaterThan(String value) {
            addCriterion("audit_price_unit >", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitGreaterThanOrEqualTo(String value) {
            addCriterion("audit_price_unit >=", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitLessThan(String value) {
            addCriterion("audit_price_unit <", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitLessThanOrEqualTo(String value) {
            addCriterion("audit_price_unit <=", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitLike(String value) {
            addCriterion("audit_price_unit like", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitNotLike(String value) {
            addCriterion("audit_price_unit not like", value, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitIn(List<String> values) {
            addCriterion("audit_price_unit in", values, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitNotIn(List<String> values) {
            addCriterion("audit_price_unit not in", values, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitBetween(String value1, String value2) {
            addCriterion("audit_price_unit between", value1, value2, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andAuditPriceUnitNotBetween(String value1, String value2) {
            addCriterion("audit_price_unit not between", value1, value2, "auditPriceUnit");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andContractTypeIsNull() {
            addCriterion("contract_type is null");
            return (Criteria) this;
        }

        public Criteria andContractTypeIsNotNull() {
            addCriterion("contract_type is not null");
            return (Criteria) this;
        }

        public Criteria andContractTypeEqualTo(String value) {
            addCriterion("contract_type =", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotEqualTo(String value) {
            addCriterion("contract_type <>", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeGreaterThan(String value) {
            addCriterion("contract_type >", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeGreaterThanOrEqualTo(String value) {
            addCriterion("contract_type >=", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLessThan(String value) {
            addCriterion("contract_type <", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLessThanOrEqualTo(String value) {
            addCriterion("contract_type <=", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLike(String value) {
            addCriterion("contract_type like", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotLike(String value) {
            addCriterion("contract_type not like", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeIn(List<String> values) {
            addCriterion("contract_type in", values, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotIn(List<String> values) {
            addCriterion("contract_type not in", values, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeBetween(String value1, String value2) {
            addCriterion("contract_type between", value1, value2, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotBetween(String value1, String value2) {
            addCriterion("contract_type not between", value1, value2, "contractType");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeIsNull() {
            addCriterion("decide_time is null");
            return (Criteria) this;
        }

        public Criteria andDecideTimeIsNotNull() {
            addCriterion("decide_time is not null");
            return (Criteria) this;
        }

        public Criteria andDecideTimeEqualTo(Date value) {
            addCriterion("decide_time =", value, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeNotEqualTo(Date value) {
            addCriterion("decide_time <>", value, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeGreaterThan(Date value) {
            addCriterion("decide_time >", value, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("decide_time >=", value, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeLessThan(Date value) {
            addCriterion("decide_time <", value, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeLessThanOrEqualTo(Date value) {
            addCriterion("decide_time <=", value, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeIn(List<Date> values) {
            addCriterion("decide_time in", values, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeNotIn(List<Date> values) {
            addCriterion("decide_time not in", values, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeBetween(Date value1, Date value2) {
            addCriterion("decide_time between", value1, value2, "decideTime");
            return (Criteria) this;
        }

        public Criteria andDecideTimeNotBetween(Date value1, Date value2) {
            addCriterion("decide_time not between", value1, value2, "decideTime");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionIsNull() {
            addCriterion("progress_description is null");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionIsNotNull() {
            addCriterion("progress_description is not null");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionEqualTo(String value) {
            addCriterion("progress_description =", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionNotEqualTo(String value) {
            addCriterion("progress_description <>", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionGreaterThan(String value) {
            addCriterion("progress_description >", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("progress_description >=", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionLessThan(String value) {
            addCriterion("progress_description <", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionLessThanOrEqualTo(String value) {
            addCriterion("progress_description <=", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionLike(String value) {
            addCriterion("progress_description like", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionNotLike(String value) {
            addCriterion("progress_description not like", value, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionIn(List<String> values) {
            addCriterion("progress_description in", values, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionNotIn(List<String> values) {
            addCriterion("progress_description not in", values, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionBetween(String value1, String value2) {
            addCriterion("progress_description between", value1, value2, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andProgressDescriptionNotBetween(String value1, String value2) {
            addCriterion("progress_description not between", value1, value2, "progressDescription");
            return (Criteria) this;
        }

        public Criteria andGiveNumberIsNull() {
            addCriterion("give_number is null");
            return (Criteria) this;
        }

        public Criteria andGiveNumberIsNotNull() {
            addCriterion("give_number is not null");
            return (Criteria) this;
        }

        public Criteria andGiveNumberEqualTo(Integer value) {
            addCriterion("give_number =", value, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberNotEqualTo(Integer value) {
            addCriterion("give_number <>", value, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberGreaterThan(Integer value) {
            addCriterion("give_number >", value, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("give_number >=", value, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberLessThan(Integer value) {
            addCriterion("give_number <", value, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberLessThanOrEqualTo(Integer value) {
            addCriterion("give_number <=", value, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberIn(List<Integer> values) {
            addCriterion("give_number in", values, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberNotIn(List<Integer> values) {
            addCriterion("give_number not in", values, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberBetween(Integer value1, Integer value2) {
            addCriterion("give_number between", value1, value2, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andGiveNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("give_number not between", value1, value2, "giveNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberIsNull() {
            addCriterion("audit_number is null");
            return (Criteria) this;
        }

        public Criteria andAuditNumberIsNotNull() {
            addCriterion("audit_number is not null");
            return (Criteria) this;
        }

        public Criteria andAuditNumberEqualTo(Integer value) {
            addCriterion("audit_number =", value, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberNotEqualTo(Integer value) {
            addCriterion("audit_number <>", value, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberGreaterThan(Integer value) {
            addCriterion("audit_number >", value, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_number >=", value, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberLessThan(Integer value) {
            addCriterion("audit_number <", value, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberLessThanOrEqualTo(Integer value) {
            addCriterion("audit_number <=", value, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberIn(List<Integer> values) {
            addCriterion("audit_number in", values, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberNotIn(List<Integer> values) {
            addCriterion("audit_number not in", values, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberBetween(Integer value1, Integer value2) {
            addCriterion("audit_number between", value1, value2, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andAuditNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_number not between", value1, value2, "auditNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberIsNull() {
            addCriterion("inline_number is null");
            return (Criteria) this;
        }

        public Criteria andInlineNumberIsNotNull() {
            addCriterion("inline_number is not null");
            return (Criteria) this;
        }

        public Criteria andInlineNumberEqualTo(String value) {
            addCriterion("inline_number =", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberNotEqualTo(String value) {
            addCriterion("inline_number <>", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberGreaterThan(String value) {
            addCriterion("inline_number >", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberGreaterThanOrEqualTo(String value) {
            addCriterion("inline_number >=", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberLessThan(String value) {
            addCriterion("inline_number <", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberLessThanOrEqualTo(String value) {
            addCriterion("inline_number <=", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberLike(String value) {
            addCriterion("inline_number like", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberNotLike(String value) {
            addCriterion("inline_number not like", value, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberIn(List<String> values) {
            addCriterion("inline_number in", values, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberNotIn(List<String> values) {
            addCriterion("inline_number not in", values, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberBetween(String value1, String value2) {
            addCriterion("inline_number between", value1, value2, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andInlineNumberNotBetween(String value1, String value2) {
            addCriterion("inline_number not between", value1, value2, "inlineNumber");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitIsNull() {
            addCriterion("agency_unit is null");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitIsNotNull() {
            addCriterion("agency_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitEqualTo(String value) {
            addCriterion("agency_unit =", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitNotEqualTo(String value) {
            addCriterion("agency_unit <>", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitGreaterThan(String value) {
            addCriterion("agency_unit >", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitGreaterThanOrEqualTo(String value) {
            addCriterion("agency_unit >=", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitLessThan(String value) {
            addCriterion("agency_unit <", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitLessThanOrEqualTo(String value) {
            addCriterion("agency_unit <=", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitLike(String value) {
            addCriterion("agency_unit like", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitNotLike(String value) {
            addCriterion("agency_unit not like", value, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitIn(List<String> values) {
            addCriterion("agency_unit in", values, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitNotIn(List<String> values) {
            addCriterion("agency_unit not in", values, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitBetween(String value1, String value2) {
            addCriterion("agency_unit between", value1, value2, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andAgencyUnitNotBetween(String value1, String value2) {
            addCriterion("agency_unit not between", value1, value2, "agencyUnit");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagIsNull() {
            addCriterion("coordinate_flag is null");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagIsNotNull() {
            addCriterion("coordinate_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagEqualTo(String value) {
            addCriterion("coordinate_flag =", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagNotEqualTo(String value) {
            addCriterion("coordinate_flag <>", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagGreaterThan(String value) {
            addCriterion("coordinate_flag >", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagGreaterThanOrEqualTo(String value) {
            addCriterion("coordinate_flag >=", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagLessThan(String value) {
            addCriterion("coordinate_flag <", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagLessThanOrEqualTo(String value) {
            addCriterion("coordinate_flag <=", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagLike(String value) {
            addCriterion("coordinate_flag like", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagNotLike(String value) {
            addCriterion("coordinate_flag not like", value, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagIn(List<String> values) {
            addCriterion("coordinate_flag in", values, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagNotIn(List<String> values) {
            addCriterion("coordinate_flag not in", values, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagBetween(String value1, String value2) {
            addCriterion("coordinate_flag between", value1, value2, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCoordinateFlagNotBetween(String value1, String value2) {
            addCriterion("coordinate_flag not between", value1, value2, "coordinateFlag");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
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