package com.loyalty.authentication.pojos;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "tkn"
})
public class ResponseAunthentication {
    @JsonProperty("tkn")
    private String tkn;

        /**
         * No args constructor for use in serialization
         *
         */
        public ResponseAunthentication() {
        }

        /**
         *
         * @param tkn
         *
         */
        public ResponseAunthentication(String tkn) {
            super();
            this.tkn = tkn;
        }

        @JsonProperty("tkn")
        public String getTkn() {
            return tkn;
        }

        @JsonProperty("tkn")
        public void setTkn(String tkn) {
            this.tkn = tkn;
        }


    }