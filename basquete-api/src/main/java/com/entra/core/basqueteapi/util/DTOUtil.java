package com.entra.core.basqueteapi.util;

import com.entra.core.basqueteapi.dto.JogoBasqueteDTO;
import com.entra.core.basqueteapi.model.JogoBasquete;

import java.util.List;
import java.util.stream.Collectors;

public class DTOUtil {

    private DTOUtil() {
    }

    public static List<JogoBasqueteDTO> parseToList(List<JogoBasquete> list) {
        return list.stream().map(JogoBasqueteDTO::of).collect(Collectors.toList());
    }
}
