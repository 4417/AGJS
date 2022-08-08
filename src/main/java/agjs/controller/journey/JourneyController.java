package agjs.controller.journey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.journey.JourneyFrontendVo;
import agjs.bean.journey.JourneyItemSelectVo;
import agjs.bean.journey.JourneyItemVo;
import agjs.bean.journey.JourneySearchVo;
import agjs.bean.journey.JourneyTypePo;
import agjs.bean.journey.JourneyVo;
import agjs.service.journey.JourneyItemService;
import agjs.service.journey.JourneyService;
import agjs.service.journey.JourneyTypeService;

@RestController
@RequestMapping(path = { "/main/jrn", "/admin/jrn" })
public class JourneyController {

	@Autowired
	private JourneyTypeService journeyTypeService;
	@Autowired
	private JourneyItemService journeyItemService;
	@Autowired
	private JourneyService journeyService;

	// 回傳行程類型 journeyType
	@GetMapping("/type")
	public List<JourneyTypePo> getTypes() {

//		System.out.println(journeyTypeName.get(10000)); 
		return journeyTypeService.getJourneyType();
	}

	// 訂單編號搜尋行程訂單
	@PostMapping("/item/sohid.search")
	public List<JourneyItemVo> searchJourneyItemBySohId(@RequestBody JourneyItemSelectVo journeyItemSelectVo) {

		System.out.println("訂單編號搜尋行程訂單");
		System.out.println(journeyItemSelectVo.getSohId());

		return journeyItemService.searchJourneyItemBySohId(journeyItemSelectVo.getSohId());
	}

	// 日期搜尋行程訂單
	@PostMapping("/item/date.search")
	public List<JourneyItemVo> searchJourneyItemByDate(@RequestBody JourneyItemSelectVo journeyItemSelectVo) {

		System.out.println("日期搜尋行程訂單");

//		return null;
		return journeyItemService.searchJourneyItemByDateRange(journeyItemSelectVo.getStartDate(),
				journeyItemSelectVo.getEndDate());
	}

	// 行程類型搜尋 行程訂單
	@PostMapping("/item/type")
	public List<JourneyItemVo> searchJourneyItemByType(@RequestBody String[] typeIdList) {

		System.out.println("形成類型搜尋 行程訂單");

		for (String id : typeIdList) {
			System.out.println(id);
		}
		return journeyItemService.searchJourneyItemByTypeId(typeIdList);
	}

	// 更新 行程訂單 日期
	@PostMapping("/item/date.update")
	public JourneyItemSelectVo updateJourneyItemDate(@RequestBody JourneyItemSelectVo journeyItemSelectVo) {

		System.out.println(" 更新 行程訂單 日期");

		System.out.println(journeyItemSelectVo.getJourneyItemId());
		System.out.println(journeyItemSelectVo.getUpdateDate());
		System.out.println(journeyItemService.updateJourneyItemDate(journeyItemSelectVo));
//		return null;
		return journeyItemSelectVo;
	}

//	@PostMapping("/add")
//	public List<JourneyItemVo> addJourney(@RequestBody JourneyItemSelectVo JourneyItemSelectVo) {
//
//		System.out.println("in");
//		System.out.println(JourneyItemSelectVo.getSohId());
//
//		return journeyItemService.searchJourneyItemBySohId(JourneyItemSelectVo.getSohId());
//	}

	// 新增行程
	@PostMapping("/add")
	public JourneyFrontendVo addJourney(@RequestBody JourneyFrontendVo journeyFrontendVo) {

		System.out.println("新增行程");
		System.out.println(journeyFrontendVo);
		System.out.println(journeyService.insertJourney(journeyFrontendVo));
		return journeyFrontendVo;

	}

	// 刪除行程
	@PostMapping("/delete")
	public boolean delJourney(@RequestBody String[] journeyIdList) {

		System.out.println("刪除行程");
		for (String s : journeyIdList) {
			System.out.println(s);
		}

		return journeyService.deleteByIdBatch(journeyIdList);

	}

	// 關鍵字搜尋行程
	@PostMapping("/search/keyword")
	public List<JourneyVo> searchJourneyByKeyword(@RequestBody JourneySearchVo journeySearchVo) throws Exception {

		System.out.println("關鍵字搜尋行程");

		return journeyService.searchBykeyword(journeySearchVo);

	}

	// 行程類型 搜尋行程
	@PostMapping("/search/type")
	public List<JourneyVo> searchJourneyByTypeId(@RequestBody String[] typeIdList) throws Exception {

		System.out.println("行程類型 搜尋行程");

		return journeyService.searchByTypeId(typeIdList);

	}

	// 更新行程
	@PostMapping("/update/jrn")
	public JourneyFrontendVo updateJourney(@RequestBody JourneyFrontendVo journeyFrontendVo) throws Exception {

		System.out.println("更新行程");
		System.out.println("update");
		System.out.println(journeyFrontendVo);
		System.out.println(journeyService.updateJourney(journeyFrontendVo));
		return journeyFrontendVo;

	}

}
