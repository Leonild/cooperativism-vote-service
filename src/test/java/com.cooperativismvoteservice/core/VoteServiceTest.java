package com.cooperativismvoteservice.core;

import com.cooperativismvoteservice.TestHelper;
import com.cooperativismvoteservice.api.CPFValidator;
import com.cooperativismvoteservice.api.Vote;
import com.cooperativismvoteservice.core.dao.repositoy.VoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.xml.internal.ws.policy.AssertionValidationProcessor;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    private ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private TestHelper testHelper = new TestHelper();

    @Mock
    private VoteRepository voteRepository;

    @Test
    public void testValidVoteByCPF() throws IOException {
        String cpf = "87173784000";
        String choice = "Sim";
        String cpfValidationBody = testHelper.loadJson("valid-cpf.json");

        CPFValidator cpfValidator = mapper.readValue(cpfValidationBody, CPFValidator.class);

        Assert.assertEquals(cpfValidator.getStatus(), "ABLE_TO_VOTE");

        Vote vote = new Vote(1L, cpf, choice);

        Mockito.when(voteRepository.insert(vote)).thenReturn(RandomUtils.nextLong());

        Long voteID = voteRepository.insert(vote);

        Assert.assertNotEquals(java.util.Optional.of(0), voteID);
    }

    @Test
    public void testInvalidVoteByCPF() throws IOException {
        String cpf = "";
        String choice = "Sim";
        String cpfValidationBody = testHelper.loadJson("invalid-cpf.json");

        CPFValidator cpfValidator = mapper.readValue(cpfValidationBody, CPFValidator.class);

        Assert.assertEquals(cpfValidator.getStatus(), "UNABLE_TO_VOTE");

        Vote vote = new Vote(1L, cpf, choice);

        Mockito.when(voteRepository.insert(vote)).thenReturn(RandomUtils.nextLong());

        Long voteID = voteRepository.insert(vote);

        Assert.assertNotEquals(java.util.Optional.of(0), voteID);
    }

}
