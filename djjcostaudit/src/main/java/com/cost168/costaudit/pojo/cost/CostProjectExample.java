package com.cost168.costaudit.pojo.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CostProjectExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pid like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pid not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andProjectOwnerIsNull() {
            addCriterion("project_owner is null");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerIsNotNull() {
            addCriterion("project_owner is not null");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerEqualTo(String value) {
            addCriterion("project_owner =", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerNotEqualTo(String value) {
            addCriterion("project_owner <>", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerGreaterThan(String value) {
            addCriterion("project_owner >", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("project_owner >=", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerLessThan(String value) {
            addCriterion("project_owner <", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerLessThanOrEqualTo(String value) {
            addCriterion("project_owner <=", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerLike(String value) {
            addCriterion("project_owner like", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerNotLike(String value) {
            addCriterion("project_owner not like", value, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerIn(List<String> values) {
            addCriterion("project_owner in", values, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerNotIn(List<String> values) {
            addCriterion("project_owner not in", values, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerBetween(String value1, String value2) {
            addCriterion("project_owner between", value1, value2, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andProjectOwnerNotBetween(String value1, String value2) {
            addCriterion("project_owner not between", value1, value2, "projectOwner");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdIsNull() {
            addCriterion("person_liable_id is null");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdIsNotNull() {
            addCriterion("person_liable_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdEqualTo(String value) {
            addCriterion("person_liable_id =", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdNotEqualTo(String value) {
            addCriterion("person_liable_id <>", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdGreaterThan(String value) {
            addCriterion("person_liable_id >", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdGreaterThanOrEqualTo(String value) {
            addCriterion("person_liable_id >=", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdLessThan(String value) {
            addCriterion("person_liable_id <", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdLessThanOrEqualTo(String value) {
            addCriterion("person_liable_id <=", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdLike(String value) {
            addCriterion("person_liable_id like", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdNotLike(String value) {
            addCriterion("person_liable_id not like", value, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdIn(List<String> values) {
            addCriterion("person_liable_id in", values, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdNotIn(List<String> values) {
            addCriterion("person_liable_id not in", values, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdBetween(String value1, String value2) {
            addCriterion("person_liable_id between", value1, value2, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andPersonLiableIdNotBetween(String value1, String value2) {
            addCriterion("person_liable_id not between", value1, value2, "personLiableId");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberIsNull() {
            addCriterion("lxpf_number is null");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberIsNotNull() {
            addCriterion("lxpf_number is not null");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberEqualTo(String value) {
            addCriterion("lxpf_number =", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberNotEqualTo(String value) {
            addCriterion("lxpf_number <>", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberGreaterThan(String value) {
            addCriterion("lxpf_number >", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberGreaterThanOrEqualTo(String value) {
            addCriterion("lxpf_number >=", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberLessThan(String value) {
            addCriterion("lxpf_number <", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberLessThanOrEqualTo(String value) {
            addCriterion("lxpf_number <=", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberLike(String value) {
            addCriterion("lxpf_number like", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberNotLike(String value) {
            addCriterion("lxpf_number not like", value, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberIn(List<String> values) {
            addCriterion("lxpf_number in", values, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberNotIn(List<String> values) {
            addCriterion("lxpf_number not in", values, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberBetween(String value1, String value2) {
            addCriterion("lxpf_number between", value1, value2, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andLxpfNumberNotBetween(String value1, String value2) {
            addCriterion("lxpf_number not between", value1, value2, "lxpfNumber");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIsNull() {
            addCriterion("project_category_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIsNotNull() {
            addCriterion("project_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdEqualTo(String value) {
            addCriterion("project_category_id =", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotEqualTo(String value) {
            addCriterion("project_category_id <>", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdGreaterThan(String value) {
            addCriterion("project_category_id >", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_category_id >=", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLessThan(String value) {
            addCriterion("project_category_id <", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("project_category_id <=", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLike(String value) {
            addCriterion("project_category_id like", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotLike(String value) {
            addCriterion("project_category_id not like", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIn(List<String> values) {
            addCriterion("project_category_id in", values, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotIn(List<String> values) {
            addCriterion("project_category_id not in", values, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdBetween(String value1, String value2) {
            addCriterion("project_category_id between", value1, value2, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotBetween(String value1, String value2) {
            addCriterion("project_category_id not between", value1, value2, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdIsNull() {
            addCriterion("project_classification_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdIsNotNull() {
            addCriterion("project_classification_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdEqualTo(String value) {
            addCriterion("project_classification_id =", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdNotEqualTo(String value) {
            addCriterion("project_classification_id <>", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdGreaterThan(String value) {
            addCriterion("project_classification_id >", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_classification_id >=", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdLessThan(String value) {
            addCriterion("project_classification_id <", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdLessThanOrEqualTo(String value) {
            addCriterion("project_classification_id <=", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdLike(String value) {
            addCriterion("project_classification_id like", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdNotLike(String value) {
            addCriterion("project_classification_id not like", value, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdIn(List<String> values) {
            addCriterion("project_classification_id in", values, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdNotIn(List<String> values) {
            addCriterion("project_classification_id not in", values, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdBetween(String value1, String value2) {
            addCriterion("project_classification_id between", value1, value2, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectClassificationIdNotBetween(String value1, String value2) {
            addCriterion("project_classification_id not between", value1, value2, "projectClassificationId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdIsNull() {
            addCriterion("project_node_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdIsNotNull() {
            addCriterion("project_node_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdEqualTo(String value) {
            addCriterion("project_node_id =", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdNotEqualTo(String value) {
            addCriterion("project_node_id <>", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdGreaterThan(String value) {
            addCriterion("project_node_id >", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_node_id >=", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdLessThan(String value) {
            addCriterion("project_node_id <", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdLessThanOrEqualTo(String value) {
            addCriterion("project_node_id <=", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdLike(String value) {
            addCriterion("project_node_id like", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdNotLike(String value) {
            addCriterion("project_node_id not like", value, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdIn(List<String> values) {
            addCriterion("project_node_id in", values, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdNotIn(List<String> values) {
            addCriterion("project_node_id not in", values, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdBetween(String value1, String value2) {
            addCriterion("project_node_id between", value1, value2, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andProjectNodeIdNotBetween(String value1, String value2) {
            addCriterion("project_node_id not between", value1, value2, "projectNodeId");
            return (Criteria) this;
        }

        public Criteria andNodeMemoIsNull() {
            addCriterion("node_memo is null");
            return (Criteria) this;
        }

        public Criteria andNodeMemoIsNotNull() {
            addCriterion("node_memo is not null");
            return (Criteria) this;
        }

        public Criteria andNodeMemoEqualTo(String value) {
            addCriterion("node_memo =", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoNotEqualTo(String value) {
            addCriterion("node_memo <>", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoGreaterThan(String value) {
            addCriterion("node_memo >", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoGreaterThanOrEqualTo(String value) {
            addCriterion("node_memo >=", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoLessThan(String value) {
            addCriterion("node_memo <", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoLessThanOrEqualTo(String value) {
            addCriterion("node_memo <=", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoLike(String value) {
            addCriterion("node_memo like", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoNotLike(String value) {
            addCriterion("node_memo not like", value, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoIn(List<String> values) {
            addCriterion("node_memo in", values, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoNotIn(List<String> values) {
            addCriterion("node_memo not in", values, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoBetween(String value1, String value2) {
            addCriterion("node_memo between", value1, value2, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeMemoNotBetween(String value1, String value2) {
            addCriterion("node_memo not between", value1, value2, "nodeMemo");
            return (Criteria) this;
        }

        public Criteria andNodeTimeIsNull() {
            addCriterion("node_time is null");
            return (Criteria) this;
        }

        public Criteria andNodeTimeIsNotNull() {
            addCriterion("node_time is not null");
            return (Criteria) this;
        }

        public Criteria andNodeTimeEqualTo(Date value) {
            addCriterion("node_time =", value, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeNotEqualTo(Date value) {
            addCriterion("node_time <>", value, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeGreaterThan(Date value) {
            addCriterion("node_time >", value, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("node_time >=", value, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeLessThan(Date value) {
            addCriterion("node_time <", value, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeLessThanOrEqualTo(Date value) {
            addCriterion("node_time <=", value, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeIn(List<Date> values) {
            addCriterion("node_time in", values, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeNotIn(List<Date> values) {
            addCriterion("node_time not in", values, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeBetween(Date value1, Date value2) {
            addCriterion("node_time between", value1, value2, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andNodeTimeNotBetween(Date value1, Date value2) {
            addCriterion("node_time not between", value1, value2, "nodeTime");
            return (Criteria) this;
        }

        public Criteria andXjGsJeIsNull() {
            addCriterion("xj_gs_je is null");
            return (Criteria) this;
        }

        public Criteria andXjGsJeIsNotNull() {
            addCriterion("xj_gs_je is not null");
            return (Criteria) this;
        }

        public Criteria andXjGsJeEqualTo(BigDecimal value) {
            addCriterion("xj_gs_je =", value, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeNotEqualTo(BigDecimal value) {
            addCriterion("xj_gs_je <>", value, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeGreaterThan(BigDecimal value) {
            addCriterion("xj_gs_je >", value, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("xj_gs_je >=", value, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeLessThan(BigDecimal value) {
            addCriterion("xj_gs_je <", value, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("xj_gs_je <=", value, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeIn(List<BigDecimal> values) {
            addCriterion("xj_gs_je in", values, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeNotIn(List<BigDecimal> values) {
            addCriterion("xj_gs_je not in", values, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xj_gs_je between", value1, value2, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andXjGsJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("xj_gs_je not between", value1, value2, "xjGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeIsNull() {
            addCriterion("ky_gs_je is null");
            return (Criteria) this;
        }

        public Criteria andKyGsJeIsNotNull() {
            addCriterion("ky_gs_je is not null");
            return (Criteria) this;
        }

        public Criteria andKyGsJeEqualTo(BigDecimal value) {
            addCriterion("ky_gs_je =", value, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeNotEqualTo(BigDecimal value) {
            addCriterion("ky_gs_je <>", value, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeGreaterThan(BigDecimal value) {
            addCriterion("ky_gs_je >", value, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ky_gs_je >=", value, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeLessThan(BigDecimal value) {
            addCriterion("ky_gs_je <", value, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ky_gs_je <=", value, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeIn(List<BigDecimal> values) {
            addCriterion("ky_gs_je in", values, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeNotIn(List<BigDecimal> values) {
            addCriterion("ky_gs_je not in", values, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ky_gs_je between", value1, value2, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andKyGsJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ky_gs_je not between", value1, value2, "kyGsJe");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeIsNull() {
            addCriterion("plan_over_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeIsNotNull() {
            addCriterion("plan_over_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeEqualTo(Date value) {
            addCriterion("plan_over_time =", value, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeNotEqualTo(Date value) {
            addCriterion("plan_over_time <>", value, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeGreaterThan(Date value) {
            addCriterion("plan_over_time >", value, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_over_time >=", value, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeLessThan(Date value) {
            addCriterion("plan_over_time <", value, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_over_time <=", value, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeIn(List<Date> values) {
            addCriterion("plan_over_time in", values, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeNotIn(List<Date> values) {
            addCriterion("plan_over_time not in", values, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeBetween(Date value1, Date value2) {
            addCriterion("plan_over_time between", value1, value2, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andPlanOverTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_over_time not between", value1, value2, "planOverTime");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementIsNull() {
            addCriterion("project_management_agreement is null");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementIsNotNull() {
            addCriterion("project_management_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementEqualTo(String value) {
            addCriterion("project_management_agreement =", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementNotEqualTo(String value) {
            addCriterion("project_management_agreement <>", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementGreaterThan(String value) {
            addCriterion("project_management_agreement >", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("project_management_agreement >=", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementLessThan(String value) {
            addCriterion("project_management_agreement <", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementLessThanOrEqualTo(String value) {
            addCriterion("project_management_agreement <=", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementLike(String value) {
            addCriterion("project_management_agreement like", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementNotLike(String value) {
            addCriterion("project_management_agreement not like", value, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementIn(List<String> values) {
            addCriterion("project_management_agreement in", values, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementNotIn(List<String> values) {
            addCriterion("project_management_agreement not in", values, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementBetween(String value1, String value2) {
            addCriterion("project_management_agreement between", value1, value2, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andProjectManagementAgreementNotBetween(String value1, String value2) {
            addCriterion("project_management_agreement not between", value1, value2, "projectManagementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementIsNull() {
            addCriterion("settlement_agreement is null");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementIsNotNull() {
            addCriterion("settlement_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementEqualTo(String value) {
            addCriterion("settlement_agreement =", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementNotEqualTo(String value) {
            addCriterion("settlement_agreement <>", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementGreaterThan(String value) {
            addCriterion("settlement_agreement >", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_agreement >=", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementLessThan(String value) {
            addCriterion("settlement_agreement <", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementLessThanOrEqualTo(String value) {
            addCriterion("settlement_agreement <=", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementLike(String value) {
            addCriterion("settlement_agreement like", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementNotLike(String value) {
            addCriterion("settlement_agreement not like", value, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementIn(List<String> values) {
            addCriterion("settlement_agreement in", values, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementNotIn(List<String> values) {
            addCriterion("settlement_agreement not in", values, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementBetween(String value1, String value2) {
            addCriterion("settlement_agreement between", value1, value2, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementAgreementNotBetween(String value1, String value2) {
            addCriterion("settlement_agreement not between", value1, value2, "settlementAgreement");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodIsNull() {
            addCriterion("settlement_reivew_method is null");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodIsNotNull() {
            addCriterion("settlement_reivew_method is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodEqualTo(String value) {
            addCriterion("settlement_reivew_method =", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodNotEqualTo(String value) {
            addCriterion("settlement_reivew_method <>", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodGreaterThan(String value) {
            addCriterion("settlement_reivew_method >", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_reivew_method >=", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodLessThan(String value) {
            addCriterion("settlement_reivew_method <", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodLessThanOrEqualTo(String value) {
            addCriterion("settlement_reivew_method <=", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodLike(String value) {
            addCriterion("settlement_reivew_method like", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodNotLike(String value) {
            addCriterion("settlement_reivew_method not like", value, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodIn(List<String> values) {
            addCriterion("settlement_reivew_method in", values, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodNotIn(List<String> values) {
            addCriterion("settlement_reivew_method not in", values, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodBetween(String value1, String value2) {
            addCriterion("settlement_reivew_method between", value1, value2, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andSettlementReivewMethodNotBetween(String value1, String value2) {
            addCriterion("settlement_reivew_method not between", value1, value2, "settlementReivewMethod");
            return (Criteria) this;
        }

        public Criteria andProjectLocationIsNull() {
            addCriterion("project_location is null");
            return (Criteria) this;
        }

        public Criteria andProjectLocationIsNotNull() {
            addCriterion("project_location is not null");
            return (Criteria) this;
        }

        public Criteria andProjectLocationEqualTo(String value) {
            addCriterion("project_location =", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationNotEqualTo(String value) {
            addCriterion("project_location <>", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationGreaterThan(String value) {
            addCriterion("project_location >", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationGreaterThanOrEqualTo(String value) {
            addCriterion("project_location >=", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationLessThan(String value) {
            addCriterion("project_location <", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationLessThanOrEqualTo(String value) {
            addCriterion("project_location <=", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationLike(String value) {
            addCriterion("project_location like", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationNotLike(String value) {
            addCriterion("project_location not like", value, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationIn(List<String> values) {
            addCriterion("project_location in", values, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationNotIn(List<String> values) {
            addCriterion("project_location not in", values, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationBetween(String value1, String value2) {
            addCriterion("project_location between", value1, value2, "projectLocation");
            return (Criteria) this;
        }

        public Criteria andProjectLocationNotBetween(String value1, String value2) {
            addCriterion("project_location not between", value1, value2, "projectLocation");
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

        public Criteria andProjectIntroductionIsNull() {
            addCriterion("project_introduction is null");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionIsNotNull() {
            addCriterion("project_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionEqualTo(String value) {
            addCriterion("project_introduction =", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionNotEqualTo(String value) {
            addCriterion("project_introduction <>", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionGreaterThan(String value) {
            addCriterion("project_introduction >", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("project_introduction >=", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionLessThan(String value) {
            addCriterion("project_introduction <", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionLessThanOrEqualTo(String value) {
            addCriterion("project_introduction <=", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionLike(String value) {
            addCriterion("project_introduction like", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionNotLike(String value) {
            addCriterion("project_introduction not like", value, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionIn(List<String> values) {
            addCriterion("project_introduction in", values, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionNotIn(List<String> values) {
            addCriterion("project_introduction not in", values, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionBetween(String value1, String value2) {
            addCriterion("project_introduction between", value1, value2, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andProjectIntroductionNotBetween(String value1, String value2) {
            addCriterion("project_introduction not between", value1, value2, "projectIntroduction");
            return (Criteria) this;
        }

        public Criteria andSumGsJeIsNull() {
            addCriterion("sum_gs_je is null");
            return (Criteria) this;
        }

        public Criteria andSumGsJeIsNotNull() {
            addCriterion("sum_gs_je is not null");
            return (Criteria) this;
        }

        public Criteria andSumGsJeEqualTo(BigDecimal value) {
            addCriterion("sum_gs_je =", value, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeNotEqualTo(BigDecimal value) {
            addCriterion("sum_gs_je <>", value, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeGreaterThan(BigDecimal value) {
            addCriterion("sum_gs_je >", value, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_gs_je >=", value, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeLessThan(BigDecimal value) {
            addCriterion("sum_gs_je <", value, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_gs_je <=", value, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeIn(List<BigDecimal> values) {
            addCriterion("sum_gs_je in", values, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeNotIn(List<BigDecimal> values) {
            addCriterion("sum_gs_je not in", values, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_gs_je between", value1, value2, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumGsJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_gs_je not between", value1, value2, "sumGsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeIsNull() {
            addCriterion("sum_ys_je is null");
            return (Criteria) this;
        }

        public Criteria andSumYsJeIsNotNull() {
            addCriterion("sum_ys_je is not null");
            return (Criteria) this;
        }

        public Criteria andSumYsJeEqualTo(BigDecimal value) {
            addCriterion("sum_ys_je =", value, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeNotEqualTo(BigDecimal value) {
            addCriterion("sum_ys_je <>", value, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeGreaterThan(BigDecimal value) {
            addCriterion("sum_ys_je >", value, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_ys_je >=", value, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeLessThan(BigDecimal value) {
            addCriterion("sum_ys_je <", value, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_ys_je <=", value, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeIn(List<BigDecimal> values) {
            addCriterion("sum_ys_je in", values, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeNotIn(List<BigDecimal> values) {
            addCriterion("sum_ys_je not in", values, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_ys_je between", value1, value2, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumYsJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_ys_je not between", value1, value2, "sumYsJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeIsNull() {
            addCriterion("sum_kzj_je is null");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeIsNotNull() {
            addCriterion("sum_kzj_je is not null");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeEqualTo(BigDecimal value) {
            addCriterion("sum_kzj_je =", value, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeNotEqualTo(BigDecimal value) {
            addCriterion("sum_kzj_je <>", value, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeGreaterThan(BigDecimal value) {
            addCriterion("sum_kzj_je >", value, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_kzj_je >=", value, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeLessThan(BigDecimal value) {
            addCriterion("sum_kzj_je <", value, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_kzj_je <=", value, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeIn(List<BigDecimal> values) {
            addCriterion("sum_kzj_je in", values, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeNotIn(List<BigDecimal> values) {
            addCriterion("sum_kzj_je not in", values, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_kzj_je between", value1, value2, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumKzjJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_kzj_je not between", value1, value2, "sumKzjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeIsNull() {
            addCriterion("sum_htj_je is null");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeIsNotNull() {
            addCriterion("sum_htj_je is not null");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeEqualTo(BigDecimal value) {
            addCriterion("sum_htj_je =", value, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeNotEqualTo(BigDecimal value) {
            addCriterion("sum_htj_je <>", value, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeGreaterThan(BigDecimal value) {
            addCriterion("sum_htj_je >", value, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_htj_je >=", value, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeLessThan(BigDecimal value) {
            addCriterion("sum_htj_je <", value, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_htj_je <=", value, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeIn(List<BigDecimal> values) {
            addCriterion("sum_htj_je in", values, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeNotIn(List<BigDecimal> values) {
            addCriterion("sum_htj_je not in", values, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_htj_je between", value1, value2, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumHtjJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_htj_je not between", value1, value2, "sumHtjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeIsNull() {
            addCriterion("sum_bgj_je is null");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeIsNotNull() {
            addCriterion("sum_bgj_je is not null");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeEqualTo(BigDecimal value) {
            addCriterion("sum_bgj_je =", value, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeNotEqualTo(BigDecimal value) {
            addCriterion("sum_bgj_je <>", value, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeGreaterThan(BigDecimal value) {
            addCriterion("sum_bgj_je >", value, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_bgj_je >=", value, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeLessThan(BigDecimal value) {
            addCriterion("sum_bgj_je <", value, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_bgj_je <=", value, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeIn(List<BigDecimal> values) {
            addCriterion("sum_bgj_je in", values, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeNotIn(List<BigDecimal> values) {
            addCriterion("sum_bgj_je not in", values, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_bgj_je between", value1, value2, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumBgjJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_bgj_je not between", value1, value2, "sumBgjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeIsNull() {
            addCriterion("sum_jsj_je is null");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeIsNotNull() {
            addCriterion("sum_jsj_je is not null");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeEqualTo(BigDecimal value) {
            addCriterion("sum_jsj_je =", value, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeNotEqualTo(BigDecimal value) {
            addCriterion("sum_jsj_je <>", value, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeGreaterThan(BigDecimal value) {
            addCriterion("sum_jsj_je >", value, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_jsj_je >=", value, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeLessThan(BigDecimal value) {
            addCriterion("sum_jsj_je <", value, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_jsj_je <=", value, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeIn(List<BigDecimal> values) {
            addCriterion("sum_jsj_je in", values, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeNotIn(List<BigDecimal> values) {
            addCriterion("sum_jsj_je not in", values, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_jsj_je between", value1, value2, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andSumJsjJeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_jsj_je not between", value1, value2, "sumJsjJe");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeIsNull() {
            addCriterion("creator_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeIsNotNull() {
            addCriterion("creator_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeEqualTo(Date value) {
            addCriterion("creator_time =", value, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeNotEqualTo(Date value) {
            addCriterion("creator_time <>", value, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeGreaterThan(Date value) {
            addCriterion("creator_time >", value, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creator_time >=", value, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeLessThan(Date value) {
            addCriterion("creator_time <", value, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeLessThanOrEqualTo(Date value) {
            addCriterion("creator_time <=", value, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeIn(List<Date> values) {
            addCriterion("creator_time in", values, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeNotIn(List<Date> values) {
            addCriterion("creator_time not in", values, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeBetween(Date value1, Date value2) {
            addCriterion("creator_time between", value1, value2, "creatorTime");
            return (Criteria) this;
        }

        public Criteria andCreatorTimeNotBetween(Date value1, Date value2) {
            addCriterion("creator_time not between", value1, value2, "creatorTime");
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