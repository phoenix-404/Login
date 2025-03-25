package appuser;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
	
	@Id
	@SequenceGenerator(
		name = "student_sequence",
		sequenceName = "student_sequence",
		allocationSize = 1
	)
//	preferred way to generate primary key values and uses a database sequence to generate unique values.
//	sequenceName is the name of the sequence in the DB. 
//	We need to specify allocationSize which needs to be the same value that the DB sequence uses as its "auto increment".
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "student_sequence"
	)
	private Long id;
	private String name;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	
	
	public AppUser(Long id, String name, String email, String password, AppUserRole appUserRole) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
