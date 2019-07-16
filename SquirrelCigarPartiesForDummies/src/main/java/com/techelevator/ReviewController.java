package com.techelevator;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.Review;
import com.techelevator.model.ReviewDao;

@Controller 
public class ReviewController {
	
	@Autowired
	private ReviewDao reviewDao;

	@RequestMapping(path="/home", method=RequestMethod.GET)
	public String bookReview(ModelMap modelMap) {
		modelMap.put("reviews", reviewDao.getAllReviews());
		return "/home";
	}
	
	@RequestMapping(path="/reviewPost", method=RequestMethod.GET)
	public String reviewPost() {
		return "reviewPost";
	}
	
	@RequestMapping(path="/reviewPost", method=RequestMethod.POST)
	public String reviewResult(@ModelAttribute Review review) {
		review.setDateSubmitted(LocalDateTime.now());
		reviewDao.save(review);
		return "redirect:/home";
	}
}
