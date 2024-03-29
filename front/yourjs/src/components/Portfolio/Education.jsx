//교육사항
import React, {useState, useEffect} from 'react';
import {Container, ContentTitle, ContentSet, Content, LeftBox, CenterBox, RightBoxes, RightBox, RightBoxTitle, RightBoxContent, Hr, NoData} from '../../common/PorfoStyled';
import { apis } from '../../common/apis';
import axiosInstance from '../../common/customAxios';


const Education = () => {
  const [viewData, setViewData] = useState([]);

  useEffect(() => {
    axiosInstance
      .get(apis.education)
      .then(response => {
        setViewData(response.data);
      })
  }, []);

  return (
    <Container id='4'>
      <ContentTitle>🔥 교육사항</ContentTitle>
      <ContentSet>
        <Hr></Hr>
        {viewData.length ? 
          <div>
            {viewData?.map((el, index) => (
              <Content key={index}>
                <LeftBox>{el.startDate}<br/>~ {el.endDate}</LeftBox>
                <CenterBox></CenterBox>
                <RightBoxes>
                  <RightBox>
                    <RightBoxTitle>교육명</RightBoxTitle>
                    <RightBoxContent>{el.eduName}</RightBoxContent>
                  </RightBox>
                  <RightBox>
                    <RightBoxTitle>주관기관</RightBoxTitle>
                    <RightBoxContent>{el.eduInstitution}</RightBoxContent>
                  </RightBox>
                  <RightBox>
                    <RightBoxTitle>교육시간</RightBoxTitle>
                    <RightBoxContent>{el.eduTime} 시간</RightBoxContent>
                  </RightBox>
                  <RightBox>
                    <RightBoxTitle>교육내용</RightBoxTitle>
                    <RightBoxContent>{el.eduContents?.split("\n").map((e, index) => <div key={index}>{e}</div>)}</RightBoxContent>
                  </RightBox>
                </RightBoxes>
              </Content>
            ))}
          </div> : 
          <NoData>등록된 정보가 없습니다.</NoData>
        }
      </ContentSet>
    </Container>
  )
};

export default Education;
