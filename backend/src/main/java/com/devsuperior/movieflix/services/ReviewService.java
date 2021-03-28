package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAll() {
		List<Review> list = repository.findAll();
		return list.stream().map(review -> new ReviewDTO(review)).collect(Collectors.toList());
	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		try {
			User user = authService.authenticated();
			authService.validadeSelfOrMenber(user.getId());
			
			//user = userRepository.getOne(dto.getUser().getId());
			UserDTO newDto = new UserDTO(user);
			
			Movie movie = movieRepository.getOne(dto.getMovieId());
			Review entity = new Review();
			entity.setText(dto.getText());
			entity.setUser(user);
			entity.setMovie(movie);
			entity = repository.save(entity);
			return new ReviewDTO(entity, newDto);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + e.getMessage());
		}
	}
}
			




















