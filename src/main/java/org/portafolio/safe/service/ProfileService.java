package org.portafolio.safe.service;

import java.util.List;

import org.portafolio.safe.entity.Profile;

public interface ProfileService {
	List<Profile> getProfileByUserId(Long id);

	List<Profile> getProfileUserByUserId(Long id);

	List<Profile> getAllProfiles();
}
