package ru.otus.homework.securityacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.otus.homework.models.Book;
import ru.otus.homework.models.Genre;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookByGenresSecurityFilter {

    private AclService aclService;

    @Autowired
    public BookByGenresSecurityFilter(AclService aclService) {
        this.aclService = aclService;
    }

    public boolean filterBooks(Authentication authentication, List<Book> books) {
        UserDetails details = ((UserDetails) authentication.getPrincipal());

        List<GrantedAuthoritySid> authorities = details.getAuthorities().stream().map(GrantedAuthoritySid::new).collect(Collectors.toList());

        List<Sid> sids = new ArrayList<>();
        sids.add(new PrincipalSid(details.getUsername()));
        sids.addAll(authorities);

        books.removeIf(book -> !filter(sids, book));
        return true;
    }

    private boolean filter(List<Sid> sids, Book book) {
        List<Permission> permissions = Collections.singletonList(BasePermission.READ);

        for (Genre g: book.getGenres()) {
            ObjectIdentity oid = new ObjectIdentityImpl(Genre.class, g.getId());
            Acl acl;
            try {
                acl = aclService.readAclById(oid);
            } catch (NotFoundException e) {
                continue;
            }

            if (!acl.isGranted(permissions, sids, false) && !sids.contains(acl.getOwner())) {
                return false;
            }
        }
        return true;
    }
}
