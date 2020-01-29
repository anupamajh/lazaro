//
//  GroupDetailPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GroupDetailPage: View {
    @ObservedObject var viewRouter: ViewRouter
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LANDING_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "INTEREST GROUPS")
                        Spacer()
                    }.padding()
                    VStack{
                        HStack{
                            VStack{
                                HStack{
                                    Text("Outdoor Adventure")
                                        .foregroundColor(Color("brownGrey"))
                                        .font(Fonts.RobotRegular)
                                    Spacer()
                                }
                                HStack{
                                    Text("CYCLING") .foregroundColor(Color("greyishBrownFour"))
                                        .font(Fonts.RobotButtonFont)
                                    Spacer()
                                }
                            }
                            Spacer()
                            Button(action:{}){
                                GuestJiniButtonText(buttonText: "SUBSCRIBE")
                            }
                        }
                        VStack{
                            GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dignissim felis et magna mattis finibus. Nulla elit ligula, placerat tincidunt ipsum eu, ornare semper felis. Duis arcu massa, venenatis eget ante sodales, posuere volutpat risus. Aenean et justo eu massa sodales posuere. Nullam sollicitudin nibh in turpis placerat venenatis.")
                        }.padding(.top)
                            .foregroundColor(Color("brownGrey"))
                    }.padding()
                        .background(Color("whiteThree"))
                    VStack{
                        VStack{
                            HStack{
                                VStack{
                                    Image(systemName: "desktopcomputer")
                                        .resizable()
                                        .padding(.all, 11)
                                }.frame(width: 40, height: 40, alignment: .center)
                                    .foregroundColor(Color.white)
                                    .background(Color("brownGrey"))
                                    .clipped()
                                    .clipShape(Circle())
                                    .shadow(radius: 5)
                                    .padding()
                                VStack{
                                    HStack{
                                        Text("Created By")
                                            .foregroundColor(Color("brownGrey"))
                                            .font(Fonts.RobotRegularSmallText)
                                        Spacer()
                                    }
                                    HStack{
                                        Text("SYSTEM")
                                            .font(Fonts.RobotRegular)
                                            .bold()
                                            .foregroundColor(Color("greyishBrownFour"))
                                        Spacer()
                                    }
                                    HStack{
                                        Text("25-03-1981").foregroundColor(Color("brownGrey"))
                                            .font(Fonts.RobotRegularSmallText)
                                        Spacer()
                                    }
                                }
                            }.padding()
                        }.background(Color("whiteThree"))
                        VStack{
                            HStack{
                                Text("27 members")
                                    .foregroundColor(Color("brownGrey"))
                                    .font(Fonts.RobotRegularSmallText)
                                    .bold()
                                VStack{
                                    Divider()
                                }
                            }
                            GroupDetailPeopleCard(name: "", joinedDate: "", imageURL: "")
                            
                        }
                    }.padding()
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct GroupDetailPeopleCard:View{
    var name:String = ""
    var joinedDate:String  = ""
    var imageURL:String  = ""
    var body: some View {
        VStack{
            HStack{
                VStack{
                    VStack{
                        Image(systemName: "person")
                            .resizable()
                            .padding(.all, 11)
                    }.frame(width: 40, height: 40, alignment: .center)
                        .foregroundColor(Color.white)
                        .background(Color("bland"))
                        .clipped()
                        .clipShape(Circle())
                        .shadow(radius: 5)
                        .padding()
                }
                VStack{
                    HStack{
                        Text("YOU")
                            .font(Fonts.RobotRegular)
                            .bold()
                            .foregroundColor(Color("brownishGrey"))
                        Spacer()
                    }
                    HStack{
                        Text("Since 25-03-1981")
                            .font(Fonts.RobotRegularSmallText)
                            .foregroundColor(Color("brownGrey"))
                        Spacer()
                    }
                    
                }
            }
            VStack{
                Divider()
                    .padding(.leading)
            }
        }
    }
}

struct GroupDetail_page_Previews: PreviewProvider {
    static var previews: some View {
        GroupDetailPage(viewRouter:ViewRouter())
    }
}
