package com.videostore.customer.service.dto;

public class CustomerDtoImpl implements CustomerDto{

    private Integer id;
    private String name;
    private Integer age;
    private String registrationDate;

    public CustomerDtoImpl(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.registrationDate = builder.registrationDate;
    }

    public CustomerDtoImpl(){}

    public static class Builder{

        private Integer id;
        private String name;
        private Integer age;
        private String registrationDate;

        public Builder(String name){
            this.name = name;
        }

        public Builder id(Integer id){
            this.id = id;
            return this;
        }

        public Builder age(Integer age){
            this.age = age;
            return this;
        }

        public Builder registrationDate(String registrationDate){
            this.registrationDate = registrationDate;
            return this;
        }

        public CustomerDtoImpl build(){
            return new CustomerDtoImpl(this);
        }

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
}
