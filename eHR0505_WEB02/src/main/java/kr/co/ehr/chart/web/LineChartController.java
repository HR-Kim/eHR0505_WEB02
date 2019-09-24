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

import kr.co.ehr.chart.service.LineVO;

@Controller
public class LineChartController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "chart/line_chart.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String pie_chart(Model model) {
		LineVO outVO01 = new LineVO(1, 37.8, 80.8, 41.8);
		LineVO outVO02 = new LineVO(2, 30.9, 69.5, 32.4);
		LineVO outVO03 = new LineVO(3, 25.4, 57, 25.7);
		LineVO outVO04 = new LineVO(4, 11.7, 18.8, 10.5);
		LineVO outVO05 = new LineVO(5, 11.9, 17.6, 10.4);
		LineVO outVO06 = new LineVO(6, 8.8, 13.6, 7.7);
		LineVO outVO07 = new LineVO(7, 7.6, 12.3, 9.6);
		
		List<LineVO> list=new ArrayList<LineVO>();
		list.add(outVO01);
		list.add(outVO02);
		list.add(outVO03);
		list.add(outVO04);
		list.add(outVO05);
		list.add(outVO06);
		list.add(outVO07);

		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();// 배열이 필요할때

		for (int i = 0; i < list.size(); i++)// 배열
		{
			JsonArray sArray = new JsonArray();// 배열 내에 들어갈 j
			sArray.add(list.get(i).getDay());
			sArray.add(list.get(i).getEarning01());
			sArray.add(list.get(i).getEarning02());
			sArray.add(list.get(i).getEarning03());

			jArray.add(sArray);
		}
		LOG.debug("3.2=result=" + jArray.toString());

		return jArray.toString();
	}
}
