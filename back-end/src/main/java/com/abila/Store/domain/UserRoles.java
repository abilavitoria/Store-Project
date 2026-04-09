package com.abila.Store.domain;

public enum UserRoles {
    ADMIN("admin"),
    VENDEDOR("vendedor"),
    CLIENTE("cliente");

    private String role;

    UserRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
