package com.example.banking_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<KycDocument> kycDocuments = new ArrayList<>();

    // Constructor
    public Customer() {}

    // Helper Method to manage bidirectional relationship safely
    public void addKycDocument(KycDocument doc) {
        kycDocuments.add(doc);
        doc.setCustomer(this);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() {
        return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public List<KycDocument> getKycDocuments() { return kycDocuments; }
    public void setKycDocuments(List<KycDocument> kycDocuments) { this.kycDocuments = kycDocuments; }
}
