package com.mycompany.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="properties")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String address;
    @ManyToOne(fetch=FetchType.LAZY) //will not fetch user data while fetching property. by default it is eager
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity userEntity;
}
