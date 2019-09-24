package kr.co.ehr.chart.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import kr.co.ehr.chart.service.PieVO;

@Controller
public class ChartController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());



	@RequestMapping(value = "chart/pie_chart.do"
			,method   = RequestMethod.GET
			,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String pie_chart(Model model) {
		LOG.debug("pie_chart=");

		List<PieVO> list = new ArrayList<PieVO>();

		PieVO outVO01 = new PieVO("버섯", 3);
		PieVO outVO02 = new PieVO("양파", 1);
		PieVO outVO03 = new PieVO("올리브", 1);
		PieVO outVO04 = new PieVO("호박", 1);
		PieVO outVO05 = new PieVO("페페로니", 2);

		list.add(outVO01);
		list.add(outVO02);
		list.add(outVO03);
		list.add(outVO04);
		list.add(outVO05);

		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();// 배열이 필요할때

		for (int i = 0; i < list.size(); i++)// 배열
		{
			JsonArray sArray = new JsonArray();// 배열 내에 들어갈 j
			sArray.add(list.get(i).getTopping());
			sArray.add(list.get(i).getSlices());

			jArray.add(sArray);
		}

		LOG.debug("3.2=result=" + jArray.toString());

		return jArray.toString();
	}
}
