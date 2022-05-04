package nl.meritude.matchmodule.service;

import nl.meritude.matchmodule.exception.MatchException;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    public String getMatchScore(Long id) throws MatchException {
        // fout
        // new Exception(negatief getal berekend)_
        return "Ja leuk";
    }
}
