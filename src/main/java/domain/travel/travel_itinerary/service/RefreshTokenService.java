package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.domain.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);
    Optional<RefreshToken> getRefreshToken(String refreshToken);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
    void deleteRefreshToken(String token);
}
