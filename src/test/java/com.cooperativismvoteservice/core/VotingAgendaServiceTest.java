package com.cooperativismvoteservice.core;

import com.cooperativismvoteservice.api.VotingAgenda;
import com.cooperativismvoteservice.core.dao.repositoy.VotingAgendaRepository;
import com.cooperativismvoteservice.core.services.VotingAgendaService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VotingAgendaServiceTest {

    @Mock
    private VotingAgendaRepository votingAgendaRepository;

    @InjectMocks
    private VotingAgendaService votingAgendaService;

    @Test
    public void testInsertionAgenda(){
        VotingAgenda votingAgenda = new VotingAgenda("test description");

        Mockito.when(votingAgendaRepository.insert(votingAgenda)).thenReturn(RandomUtils.nextLong());

        Long votinAgendaID = votingAgendaRepository.insert(votingAgenda);

        Assert.assertNotEquals(java.util.Optional.of(0), votinAgendaID);
    }

    @Test
    public void testGetExistentAgenda(){

        Long votingAgendaID = RandomUtils.nextLong();
        VotingAgenda votingAgenda = new VotingAgenda("test description");

        Mockito.when(votingAgendaRepository.findById(votingAgendaID)).thenReturn(votingAgenda);

        votingAgenda = votingAgendaRepository.findById(votingAgendaID);

        Assert.assertNotNull(votingAgenda);


    }

    @Test
    public void testGetNoExistentAgenda(){

        Long votingAgendaID = RandomUtils.nextLong();

        VotingAgenda votingAgenda = votingAgendaRepository.findById(votingAgendaID);

        Assert.assertNull(votingAgenda);


    }

}