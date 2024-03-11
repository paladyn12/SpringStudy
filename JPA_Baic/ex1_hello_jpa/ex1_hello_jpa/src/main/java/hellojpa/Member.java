package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEO_GENERATOR")
    private Long id;

    @Column(name="name")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //날짜와 시간 모두
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //private LocalDate testLocalDate; 연월
    //private LocalDateTime testLocalDateTime; 연월일

    @Lob //큰 컨텐츠
    private String description;

    @Transient //DB 반영 X
    private int temp;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Member(){}

    public Long getId() {
        return this.id;
    }
}
