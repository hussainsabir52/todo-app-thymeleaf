package org.de.unimuenster.imi.samples.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
@Entity
public class Project
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    @Length(min = 5, max = 150)
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;


    public void setId(Integer id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setDescription(String desc){this.description=desc;}
    public void setStartDate(Date date){this.startDate=date;}
    public void setEndDate(Date date){this.endDate=date;}
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public Integer getId(){return id;}
    public String getDescription(){return description;}
    public String getName(){return name;}
    public Date getStartDate(){return startDate;}
    public Date getEndDate(){return endDate;}

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
