package com.cost168.costaudit.pojo.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkEnterpriseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkEnterpriseExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andServiceStartIsNull() {
            addCriterion("service_start is null");
            return (Criteria) this;
        }

        public Criteria andServiceStartIsNotNull() {
            addCriterion("service_start is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStartEqualTo(Date value) {
            addCriterion("service_start =", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartNotEqualTo(Date value) {
            addCriterion("service_start <>", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartGreaterThan(Date value) {
            addCriterion("service_start >", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartGreaterThanOrEqualTo(Date value) {
            addCriterion("service_start >=", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartLessThan(Date value) {
            addCriterion("service_start <", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartLessThanOrEqualTo(Date value) {
            addCriterion("service_start <=", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartIn(List<Date> values) {
            addCriterion("service_start in", values, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartNotIn(List<Date> values) {
            addCriterion("service_start not in", values, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartBetween(Date value1, Date value2) {
            addCriterion("service_start between", value1, value2, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartNotBetween(Date value1, Date value2) {
            addCriterion("service_start not between", value1, value2, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceEndIsNull() {
            addCriterion("service_end is null");
            return (Criteria) this;
        }

        public Criteria andServiceEndIsNotNull() {
            addCriterion("service_end is not null");
            return (Criteria) this;
        }

        public Criteria andServiceEndEqualTo(Date value) {
            addCriterion("service_end =", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndNotEqualTo(Date value) {
            addCriterion("service_end <>", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndGreaterThan(Date value) {
            addCriterion("service_end >", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndGreaterThanOrEqualTo(Date value) {
            addCriterion("service_end >=", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndLessThan(Date value) {
            addCriterion("service_end <", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndLessThanOrEqualTo(Date value) {
            addCriterion("service_end <=", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndIn(List<Date> values) {
            addCriterion("service_end in", values, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndNotIn(List<Date> values) {
            addCriterion("service_end not in", values, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndBetween(Date value1, Date value2) {
            addCriterion("service_end between", value1, value2, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndNotBetween(Date value1, Date value2) {
            addCriterion("service_end not between", value1, value2, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagIsNull() {
            addCriterion("effective_flag is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagIsNotNull() {
            addCriterion("effective_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagEqualTo(String value) {
            addCriterion("effective_flag =", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagNotEqualTo(String value) {
            addCriterion("effective_flag <>", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagGreaterThan(String value) {
            addCriterion("effective_flag >", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagGreaterThanOrEqualTo(String value) {
            addCriterion("effective_flag >=", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagLessThan(String value) {
            addCriterion("effective_flag <", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagLessThanOrEqualTo(String value) {
            addCriterion("effective_flag <=", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagLike(String value) {
            addCriterion("effective_flag like", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagNotLike(String value) {
            addCriterion("effective_flag not like", value, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagIn(List<String> values) {
            addCriterion("effective_flag in", values, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagNotIn(List<String> values) {
            addCriterion("effective_flag not in", values, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagBetween(String value1, String value2) {
            addCriterion("effective_flag between", value1, value2, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andEffectiveFlagNotBetween(String value1, String value2) {
            addCriterion("effective_flag not between", value1, value2, "effectiveFlag");
            return (Criteria) this;
        }

        public Criteria andSendPersonIsNull() {
            addCriterion("send_person is null");
            return (Criteria) this;
        }

        public Criteria andSendPersonIsNotNull() {
            addCriterion("send_person is not null");
            return (Criteria) this;
        }

        public Criteria andSendPersonEqualTo(Integer value) {
            addCriterion("send_person =", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotEqualTo(Integer value) {
            addCriterion("send_person <>", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonGreaterThan(Integer value) {
            addCriterion("send_person >", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_person >=", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonLessThan(Integer value) {
            addCriterion("send_person <", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonLessThanOrEqualTo(Integer value) {
            addCriterion("send_person <=", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonIn(List<Integer> values) {
            addCriterion("send_person in", values, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotIn(List<Integer> values) {
            addCriterion("send_person not in", values, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonBetween(Integer value1, Integer value2) {
            addCriterion("send_person between", value1, value2, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotBetween(Integer value1, Integer value2) {
            addCriterion("send_person not between", value1, value2, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveIsNull() {
            addCriterion("send_person_effective is null");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveIsNotNull() {
            addCriterion("send_person_effective is not null");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveEqualTo(Integer value) {
            addCriterion("send_person_effective =", value, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveNotEqualTo(Integer value) {
            addCriterion("send_person_effective <>", value, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveGreaterThan(Integer value) {
            addCriterion("send_person_effective >", value, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_person_effective >=", value, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveLessThan(Integer value) {
            addCriterion("send_person_effective <", value, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveLessThanOrEqualTo(Integer value) {
            addCriterion("send_person_effective <=", value, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveIn(List<Integer> values) {
            addCriterion("send_person_effective in", values, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveNotIn(List<Integer> values) {
            addCriterion("send_person_effective not in", values, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveBetween(Integer value1, Integer value2) {
            addCriterion("send_person_effective between", value1, value2, "sendPersonEffective");
            return (Criteria) this;
        }

        public Criteria andSendPersonEffectiveNotBetween(Integer value1, Integer value2) {
            addCriterion("send_person_effective not between", value1, value2, "sendPersonEffective");
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