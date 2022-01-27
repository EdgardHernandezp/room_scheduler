package com.tyco.room_scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RoomSchedulerApplicationTests {
	@MockBean
	SchedulingService schedService;

	@Autowired
	TestRestTemplate restClient;

	@LocalServerPort
	private int port;

	static final String localhost = "http://localhost:";

	@Test
	void whenCreateUser_returnUser() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(2022, 1, 26, 8, 30);

		Meeting expectedMeeting = new Meeting();
		expectedMeeting.setId(1);
		Date startDate = calendar.getTime();
		expectedMeeting.setStartDate(startDate);
		// expectedMeeting.setEndDate(calendar.getTime().getTime() + );

		when(schedService.scheduleMeetingRoom(1, calendar.getTime(), 30)).thenReturn(expectedMeeting);

		Meeting actualMeeting = restClient.postForObject(
				localhost + port + "/create?roomId={0}&startDate={1}&meetingTimeSpanInMinutes={2}", expectedMeeting,
				Meeting.class, 1,
				startDate, 30);

		assertNotNull(actualMeeting);
		assertEquals(expectedMeeting, actualMeeting);
	}

}
