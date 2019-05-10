package com.loyalty.authentication.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "user",
            "password"
    })
    public class RequestValidaCliente {

        @JsonProperty("user")
        private String user;
        @JsonProperty("password")
        private String password;

        /**
         * No args constructor for use in serialization
         *
         */
        public RequestValidaCliente() {
        }

        /**
         *
         * @param password
         * @param user
         */
        public RequestValidaCliente(String user, String password) {
            super();
            this.user = user;
            this.password = password;
        }

        @JsonProperty("user")
        public String getUser() {
            return user;
        }

        @JsonProperty("user")
        public void setUser(String user) {
            this.user = user;
        }

        @JsonProperty("password")
        public String getPassword() {
            return password;
        }

        @JsonProperty("password")
        public void setPassword(String password) {
            this.password = password;
        }

    }
