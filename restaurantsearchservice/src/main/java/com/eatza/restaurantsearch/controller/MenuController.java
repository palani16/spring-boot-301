//package com.eatza.restaurantsearch.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.eatza.restaurantsearch.dto.ItemRequestDto;
//import com.eatza.restaurantsearch.exception.ItemNotFoundException;
//import com.eatza.restaurantsearch.model.Menu;
//import com.eatza.restaurantsearch.model.MenuItem;
//import com.eatza.restaurantsearch.model.Restaurant;
//import com.eatza.restaurantsearch.service.menuitemservice.MenuItemService;
//import com.eatza.restaurantsearch.service.menuservice.MenuService;
//
//@RestController
//public class MenuController {
//	
//	@Autowired
//	MenuService menuService;
//	private static final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
//
//	
//	@PostMapping("/menu")
//	public ResponseEntity<String> addItemsToRestaurantMenu(@RequestHeader String authorization,@RequestBody Menu menu){
//
//		logger.debug("In addItemsToRestaurantMenu method");
//		menuService.saveMenu(menu);
//		logger.debug("Item added successfully");
//		return ResponseEntity
//				.status(HttpStatus.OK)
//				.body("Item Added successfully");
//
//	}
//
//
////	@GetMapping("/menu/all")
////	public List<Menu> getAllMenu(){
////		logger.debug("In getItemById method, calling service");
////		return (List<Menu>) menuService.getMenuById(1L);
////		
////	}
//}
