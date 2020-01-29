//
//  CommunityLanding.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct CommunityLanding: View {
    @ObservedObject var viewRouter: ViewRouter
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    VStack{
                        Button(action:{
                            self.viewRouter.returnPage = ViewRoutes.COMMUNIT_LANDING_PAGE
                            self.viewRouter.currentPage = ViewRoutes.MY_PROFILE_VIEW
                        }){
                            CommunityLandingPageCard(
                                title: "MY PROFILE",
                                description: "Based on your profile, you will be able to connect with the best matches who share your interests. Go ahead and setup your profile."
                            )
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            self.viewRouter.returnPage = ""
                            self.viewRouter.currentPage = ViewRoutes.PEOPLE_LIST_PAGE
                        }){
                            CommunityLandingPageCard(
                                title: "PEOPLE",
                                description: "Meet the community members and make new friends."
                            )
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            self.viewRouter.returnPage = ""
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LANDING_PAGE
                        }){
                            CommunityLandingPageCard(
                                title: "GROUPS",
                                description: "Join the groups and catch all the action. You will get suggestions based on your profile."
                            )
                        }
                    }
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct CommunityLandingPageCard: View {
    var title:String = ""
    var description:String = ""
    
    var body: some View {
        VStack{
            VStack{
                HStack{
                    VStack{
                        Text("")
                    }.frame(minWidth: 25, maxWidth: 25, minHeight: 4, maxHeight: 4)
                        .background(Color("pastelRed"))
                        .overlay(
                            Rectangle()
                                .strokeBorder(
                                    style: StrokeStyle(
                                        lineWidth: 1,
                                        dash: [4]
                                    )
                            )
                                .foregroundColor(Color("pastelRed"))
                    ).padding(.top)
                        .padding(.leading)
                    Spacer()
                }
                HStack{
                    Text(self.title)
                        .font(Fonts.RobotButtonFont)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                    Spacer()
                }.padding(.leading)
                    .padding(.top, 5)
                HStack{
                    Text(self.description)
                        .font(Fonts.RobotRegularSmallText)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                        .multilineTextAlignment(.leading)
                    Spacer()
                }.padding()
            }.background(Color("whiteThree"))
                .shadow(radius: 10)
        }.padding(.all, 25)
    }
    
}


struct CommunityLanding_Previews: PreviewProvider {
    static var previews: some View {
        CommunityLanding(viewRouter: ViewRouter())
    }
}
