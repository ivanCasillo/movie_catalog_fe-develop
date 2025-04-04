package it.step.entity;

public enum Roles {
    ADMIN ("1"),
    PUBLIC ("2"),
    SUPER_ADMIN ("3");

    private String code;
    private Roles(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }

}
