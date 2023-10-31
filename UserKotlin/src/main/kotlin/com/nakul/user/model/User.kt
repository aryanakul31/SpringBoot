package com.nakul.user.model

import jakarta.persistence.*

//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//import java.util.stream.Collectors

@Entity
@Table
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int,

    val name: String,

    @Column(unique = true) var email: String,

    var password: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "user_id")
    val addresses: Set<Address>


//    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "user_role",
//        joinColumns = [JoinColumn(name = "user_id")],
//        inverseJoinColumns = [JoinColumn(name = "role_id")]
//    )
//    var roles: HashSet<Role> = hashSetOf()
) {
    override fun toString(): String {
        return "UserModel(id=$id, name='$name', email='$email', password='$password')"
    }
}
//    : UserDetails {
//    override fun toString(): String {
//        return "UserModel(id=$id, name='$name', email='$email', password='$myPassword')"
//    }
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        return roles.stream().map { SimpleGrantedAuthority(it) }.collect(Collectors.toList())
//    }
//
//    override fun getPassword(): String = myPassword
//
//    override fun getUsername(): String = email
//
//    override fun isAccountNonExpired(): Boolean = true
//
//    override fun isAccountNonLocked(): Boolean = true
//
//    override fun isCredentialsNonExpired(): Boolean = true
//
//    override fun isEnabled(): Boolean = true
//}
