package uz.pdp.warehousemanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.warehousemanagementsystem.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbsEntity {
}
