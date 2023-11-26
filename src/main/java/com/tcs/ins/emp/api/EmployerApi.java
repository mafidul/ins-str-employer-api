package com.tcs.ins.emp.api;

import static com.tcs.ins.support.Constant.REQUEST_PARAM_MAPPING;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_PAGE_NUMBER;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_PAGE_SIZE;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_SORT_BY;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_SORT_DIRECTION;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.ins.emp.service.EmployerService;
import com.tcs.ins.emp.service.model.EmployerSearchRequest;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;

@RestController
@RequestMapping(REQUEST_PARAM_MAPPING)
public class EmployerApi {

	private static final String SORT_DIRECTION_ASC = "asc";

	private final EmployerService employerService;

	public EmployerApi(EmployerService employerService) {
		this.employerService = employerService;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployerApi.class);

	@PostMapping(path = "/login/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<LoginDetailModel> login(@RequestBody LoginDetailModel loginModel) {
		LoginDetailModel login = employerService.login(loginModel);
		return new ResponseEntity<LoginDetailModel>(login, HttpStatus.OK);
	}

	@PostMapping(path = "/profile/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<LoginModel> createLogin(@RequestBody @Valid LoginModel loginModel, UriComponentsBuilder ucb) {
		LoginModel create = employerService.createLogin(loginModel);
		return ResponseEntity.created(ucb
					                 .path(REQUEST_PARAM_MAPPING + "/{id}")
					                 .buildAndExpand(create.getId())
					                 .toUri())
					         .body(create);
	}

	@GetMapping(path = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<LoginModel> getProfileById(@PathVariable Long id) {
		return ResponseEntity.ok(employerService.getProfileById(id));
	}

	@GetMapping(path = "/profile/search", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Page<LoginModel>> searchEmployer(
			@RequestParam(name = REQUEST_PARAM_PAGE_NUMBER, required = true) Integer pageNumber,
			@RequestParam(name = REQUEST_PARAM_PAGE_SIZE, required = true) Integer pageSize,
			@RequestParam(name = REQUEST_PARAM_SORT_BY, required = false) String sortBy,
			@RequestParam(name = REQUEST_PARAM_SORT_DIRECTION, required = false) String sortDirection,
			@RequestParam Map<String, String> requestParam) {
		EmployerSearchRequest searchRequest = new EmployerSearchRequest(requestParam);
		PageRequest pageRequest = pageRequest(pageNumber, pageSize, sortBy, sortDirection);
		Page<LoginModel> page = employerService.searchEmployer(pageRequest, searchRequest);
		//LOGGER.info("Response from Search : {}" + ResponseEntity.ok(page));
		return ResponseEntity.ok(page);
	}

	private PageRequest pageRequest(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		if (StringUtils.hasText(sortBy)) {
			Direction direction = StringUtils.hasText(sortDirection)
					&& SORT_DIRECTION_ASC.equalsIgnoreCase(sortDirection) ? Direction.ASC : Direction.DESC;
			return PageRequest.of(pageNumber, pageSize, Sort.by(new Order(direction, sortBy)));
		}
		return PageRequest.of(pageNumber, pageSize);
	}

	@PutMapping(path = "/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<LoginModel> updateProfile(@PathVariable Long id, @RequestBody LoginModel LoginModel,
			UriComponentsBuilder ucb) {
		LoginModel.setId(id);
		LoginModel create = employerService.updateProfile(LoginModel);
		return ResponseEntity.ok(create);
	}

	@DeleteMapping(path = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> deleteNewPost(@PathVariable Long id) {
		employerService.deleteProfile(id);
		return ResponseEntity.ok(null);
	}
}