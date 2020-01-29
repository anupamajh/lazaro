//
//  GroupLandingPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GroupLandingPage: View {
   @ObservedObject var viewRouter: ViewRouter
     var body: some View {
            GeometryReader { geometry in
                VStack{
                    VStack{
                        VStack{
                            Button(action:{
                                self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                            }){
                                GroupLandingPageCard(
                                    title: "INTEREST GROUPS",
                                    description: "There is a group for every activity, hobby or topic. Connect with like minded people."
                                )
                            }
                        }
                        
                        VStack{
                            Button(action:{
                                self.viewRouter.returnPage = ""
                                self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                            }){
                                 GroupLandingPageCard(
                                    title: "COMMUNITY GROUPS",
                                    description: "Explore groups created by the community members. Participate and catch all the action."
                                )
                            }
                        }
                        
                        VStack{
                            Button(action:{
                                self.viewRouter.returnPage = ""
                                self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                            }){
                                GroupLandingPageCard(
                                    title: "MY GROUPS",
                                    description: "Create and manage your own groups. Host parties, events or simply bond together."
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

struct GroupLandingPageCard: View {
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


struct GroupLandingPage_Previews: PreviewProvider {
    static var previews: some View {
        GroupLandingPage(viewRouter: ViewRouter())
    }
}
