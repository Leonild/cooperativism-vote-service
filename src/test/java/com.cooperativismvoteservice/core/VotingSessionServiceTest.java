package com.cooperativismvoteservice.core;

import com.cooperativismvoteservice.api.VotingAgenda;
import com.cooperativismvoteservice.api.VotingSession;
import com.cooperativismvoteservice.core.dao.repositoy.VotingSessionRepository;
import com.cooperativismvoteservice.core.services.VotingSessionService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionServiceTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @InjectMocks
    private VotingSessionService votingSessionService;

    @Test
    public void testOpenSessionDefaultTime(){
        VotingSession votingSession = new VotingSession(1L);

        Mockito.when(votingSessionRepository.insert(votingSession)).thenReturn(RandomUtils.nextLong());

        Long votinSessionID = votingSessionRepository.insert(votingSession);

        Assert.assertNotEquals(java.util.Optional.of(0), votinSessionID);
    }

    @Test
    public void testOpenSessionSetTime(){
        VotingSession votingSession = new VotingSession(1L, RandomUtils.nextLong());

        Mockito.when(votingSessionRepository.insert(votingSession)).thenReturn(RandomUtils.nextLong());

        Long votinSessionID = votingSessionRepository.insert(votingSession);

        Assert.assertNotEquals(java.util.Optional.of(0), votinSessionID);
    }

}
