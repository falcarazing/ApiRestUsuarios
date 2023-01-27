package com.nisum.ApiRestFulUsuarios.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"phone\"")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_phone", nullable = false)
    private Long idPhone;

    @NonNull
    private String number;
    @NonNull
    private String citycode;
    @NonNull
    private String contrycode;
    @JsonIgnore
    private String iduser;

}
