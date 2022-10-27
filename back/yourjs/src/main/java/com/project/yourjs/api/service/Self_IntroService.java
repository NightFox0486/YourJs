package com.project.yourjs.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.project.yourjs.api.req.Self_IntroReq;
import com.project.yourjs.api.res.Self_IntroDeleteRes;
import com.project.yourjs.api.res.Self_IntroPatchRes;
import com.project.yourjs.api.res.Self_IntroPostRes;
import com.project.yourjs.db.entity.Notice;
import com.project.yourjs.db.entity.Self_Intro;
import com.project.yourjs.db.entity.User;
import com.project.yourjs.db.repository.NoticeRepository;
import com.project.yourjs.db.repository.Self_IntroRepository;
import com.project.yourjs.db.repository.UserRepository;

@Service
public class Self_IntroService {
    private final Self_IntroRepository self_IntroRepository;
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    public Self_IntroService(Self_IntroRepository self_IntroRepository, UserRepository userRepository,
            NoticeRepository noticeRepository) {
        this.self_IntroRepository = self_IntroRepository;
        this.userRepository = userRepository;
        this.noticeRepository = noticeRepository;
    }

    public List<Self_Intro> getAllSelf_Intro(String userId) {
        List<Self_Intro> self_IntroList = new ArrayList<Self_Intro>();
        List<Self_Intro> allSelf_IntroList = self_IntroRepository.findAll();
        for (Self_Intro self_Intro : allSelf_IntroList) {
            if (self_Intro.getUser().getUserId().equals(userId))
                self_IntroList.add(self_Intro);
        }
        return self_IntroList;
    }

    @Transactional
    public Self_IntroPostRes createSelf_IntroNoNotice(String userId, Self_IntroReq self_IntroReq) {
        Self_IntroPostRes self_IntroPostRes = new Self_IntroPostRes();
        self_IntroPostRes.setResult("fail");
        Self_Intro self_Intro = new Self_Intro();
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User user = oUser.get();
            self_Intro.setUser(user);
            self_Intro.setCoName(self_IntroReq.getCoName());
            self_Intro.setContents(self_IntroReq.getContents());
            self_Intro.setMaxBytes(self_IntroReq.getMaxBytes());
            self_Intro.setMaxLength(self_IntroReq.getMaxLength());
            self_Intro.setModDtm(LocalDateTime.now());
            self_Intro.setQuestion(self_IntroReq.getQuestion());
            self_Intro.setRegDtm(LocalDateTime.now());
            self_Intro.setNotice(null);
            self_Intro = self_IntroRepository.save(self_Intro);
            if (self_Intro != null) {
                self_IntroPostRes.setResult("success");
            }
        }
        return self_IntroPostRes;
    }

    @Transactional
    public Self_IntroPostRes createSelf_Intro(String userId, Self_IntroReq self_IntroReq, Integer noticeSeq) {
        Self_IntroPostRes self_IntroPostRes = new Self_IntroPostRes();
        self_IntroPostRes.setResult("fail");
        Self_Intro self_Intro = new Self_Intro();
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            Optional<Notice> oNotice = noticeRepository.findById(noticeSeq);
            if (oNotice.isPresent()) {
                User user = oUser.get();
                Notice notice = oNotice.get();
                self_Intro.setUser(user);
                self_Intro.setCoName(self_IntroReq.getCoName());
                self_Intro.setContents(self_IntroReq.getContents());
                self_Intro.setMaxBytes(self_IntroReq.getMaxBytes());
                self_Intro.setMaxLength(self_IntroReq.getMaxLength());
                self_Intro.setModDtm(LocalDateTime.now());
                self_Intro.setQuestion(self_IntroReq.getQuestion());
                self_Intro.setRegDtm(LocalDateTime.now());
                self_Intro.setNotice(notice);
                self_Intro = self_IntroRepository.save(self_Intro);
                if (self_Intro != null) {
                    self_IntroPostRes.setResult("success");
                }
            }
        }
        return self_IntroPostRes;
    }

    @Transactional
    public Self_IntroPatchRes updateSelf_Intro(String userId, Integer self_IntroSeq, Self_IntroReq self_IntroReq) {
        Self_IntroPatchRes self_IntroPatchRes = new Self_IntroPatchRes();
        self_IntroPatchRes.setResult("fail");
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User user = oUser.get();
            Optional<Self_Intro> oSelf_Intro = self_IntroRepository.findById(self_IntroSeq);
            if (oSelf_Intro.isPresent()) {
                Self_Intro self_Intro = new Self_Intro();
                if (self_Intro.getUser().getUserSeq() != user.getUserSeq())
                    return self_IntroPatchRes;
                if (StringUtils.isNotBlank(self_IntroReq.getCoName()))
                    self_Intro.setCoName(self_IntroReq.getCoName());
                if (StringUtils.isNotBlank(self_IntroReq.getContents()))
                    self_Intro.setContents(self_IntroReq.getContents());
                if (self_IntroReq.getMaxBytes() != 0)
                    self_Intro.setMaxBytes(self_IntroReq.getMaxBytes());
                if (self_IntroReq.getMaxLength() != 0)
                    self_Intro.setMaxLength(self_IntroReq.getMaxLength());
                self_Intro.setModDtm(LocalDateTime.now());
                if (StringUtils.isNotBlank(self_IntroReq.getQuestion()))
                    self_Intro.setQuestion(self_IntroReq.getQuestion());
                self_Intro = self_IntroRepository.save(self_Intro);
                if (self_Intro != null) {
                    self_IntroPatchRes.setResult("success");
                }
            }
        }
        return self_IntroPatchRes;
    }

    @Transactional
    public Self_IntroDeleteRes deleteSelf_Intro(String userId, Integer self_IntroSeq) {
        Self_IntroDeleteRes self_IntroDeleteRes = new Self_IntroDeleteRes();
        self_IntroDeleteRes.setResult("fail");
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User user = oUser.get();
            Optional<Self_Intro> oSelf_Intro = self_IntroRepository.findById(self_IntroSeq);
            if (oSelf_Intro.isPresent()) {
                Self_Intro self_Intro = oSelf_Intro.get();
                if (self_Intro.getUser().getUserSeq() == user.getUserSeq())
                    self_IntroRepository.deleteById(self_IntroSeq);
                self_IntroDeleteRes.setResult("success");
            }
        }
        return self_IntroDeleteRes;
    }
}