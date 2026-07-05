package redNorte.Optimizacion.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comuna")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "region", nullable = false)
    @JsonIgnoreProperties("comunas")
    private Region region;

    @OneToMany(mappedBy = "comuna", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("comuna")
    private List<Hospital> hospitales;
}
