package io.ace.test.authresourcesever.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController {



    @PreAuthorize("hasAuthority('ROLE_CREATE_NOTE')")
    @PostMapping("/create")
    public String createNote() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        return "Note Created Successfully";
    }
    @PreAuthorize("hasAuthority('ROLE_EDIT_NOTE')")
    @PutMapping("/edit")
    public String editNote() {
        return "Note Edited Successfully";
    }
    @PreAuthorize("hasAuthority('ROLE_DELETE_NOTE')")
    @DeleteMapping("/delete")
    public String deleteNote() {
        return "Note Deleteted Successfully";
    }
    @PreAuthorize("hasAuthority('ROLE_VIEW_NOTE')")
    @GetMapping("/view")
    public String viewNote() {
        return "Note View Successfully";
    }
    @PreAuthorize("hasAuthority('ROLE_VIEW_ALL_NOTE')")
    @GetMapping("/viewall")
    public String viewAllNote() {
        return "Note View All Successfully";
    }
}
