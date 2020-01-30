//
//  PeopleDetailPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct PeopleDetailPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var getPersonDetailService:GetPersonDetailService
    @ObservedObject var personAddFavouriteService:PersonAddFavouriteService
    
    @State var showAddFavouriteActivitiIndicater:Bool = false
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.getPersonDetailService = GetPersonDetailService(viewRouter: viewRouter,userId: viewRouter.primaryKey)
        self.personAddFavouriteService = PersonAddFavouriteService(viewRouter: viewRouter)
        UITableView.appearance().tableFooterView = UIView()
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    ScrollView{
                        HStack{
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.PEOPLE_LIST_PAGE
                            }) {
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                
                            }.padding(.horizontal)
                            
                            GuestJiniTitleText(title: "PEOPLE")
                            Spacer()
                        }.padding()
                        ZStack{
                            VStack{
                                
                                VStack(alignment: .center){
                                    VStack{
                                        Image(systemName: "person.fill")
                                            .resizable()
                                            .clipShape(Circle())
                                            .shadow(radius: 5)
                                    } .frame(width:50, height:50)
                                    VStack{
                                        HStack{
                                            if(self.getPersonDetailService.fetchComplete == true){
                                                if(self.getPersonDetailService.othersAddressBook.displayName != nil){
                                                    Text(self.getPersonDetailService.othersAddressBook.displayName!)
                                                        .font(Fonts.RobotFieldText)
                                                        .foregroundColor(Color("greyishBrownThree"))
                                                        .bold()
                                                }else{
                                                    Text("")
                                                        .font(Fonts.RobotFieldText)
                                                        .foregroundColor(Color("greyishBrownThree"))
                                                        .bold()
                                                }
                                            }else{
                                                Text("")
                                                    .font(Fonts.RobotFieldText)
                                                    .foregroundColor(Color("greyishBrownThree"))
                                                    .bold()
                                            }
                                        }
                                        HStack{
                                            if(self.getPersonDetailService.othersInfo.gender == 1){
                                                Text("Male")
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .foregroundColor(Color("brownishGrey"))
                                            } else  if(self.getPersonDetailService.othersInfo.gender == 2){
                                                Text("Female")
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .foregroundColor(Color("brownishGrey"))
                                            }else  if(self.getPersonDetailService.othersInfo.gender == 3){
                                                Text("Not Specified")
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .foregroundColor(Color("brownishGrey"))
                                            }else{
                                                Text("Not Specified")
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .foregroundColor(Color("brownishGrey"))
                                            }
                                        }
                                    }
                                }
                                
                                VStack(alignment:.leading){
                                    HStack{
                                        VStack{
                                            Button(action: {
                                                self.showAddFavouriteActivitiIndicater = true
                                                
                                                self.personAddFavouriteService.addToFavourite(
                                                    userId: self.viewRouter.primaryKey,
                                                    isFavourite: (self.getPersonDetailService.peopleResponse.isFavourite! == 0) ? 1 : 0
                                                ) { (response) in
                                                    self.showAddFavouriteActivitiIndicater = false
                                                    if(self.getPersonDetailService.peopleResponse.isFavourite == 1){
                                                        self.getPersonDetailService.peopleResponse.isFavourite = 0
                                                    }else{
                                                        self.getPersonDetailService.peopleResponse.isFavourite = 1
                                                    }
                                                }
                                                
                                            }){
                                                VStack{
                                                    Image(systemName: "heart")
                                                        .resizable()
                                                        .frame(width:25, height:25)
                                                        .foregroundColor(Color.white)
                                                    if(self.getPersonDetailService.peopleResponse.isFavourite == 0){
                                                        GuestJiniInformationText(information: "Add to favourites")
                                                            .foregroundColor(Color.white)
                                                    }else{
                                                        GuestJiniInformationText(information: "Remove from favourites")
                                                            .foregroundColor(Color.white)
                                                        
                                                    }
                                                } .frame(width:100, height:100)
                                            }
                                        }.background(Color("aquaMarine"))
                                            .foregroundColor(Color("aquaMarine"))
                                        VStack{
                                            HStack{
                                                Text("MOBILE")
                                                    .font(Fonts.RobotRegular)
                                                    .foregroundColor(Color("brownishGrey"))
                                                Spacer()
                                            }.padding(.top,10)
                                            HStack{
                                                if(self.getPersonDetailService.fetchComplete == true){
                                                    if(self.getPersonDetailService.othersAddressBook.phone1 != nil){
                                                        Text(self.getPersonDetailService.othersAddressBook.phone1!)
                                                            .font(Fonts.RobotFieldText)
                                                            .foregroundColor(Color("greyishBrownThree"))
                                                            .bold()
                                                    }else{
                                                        Text("")
                                                            .font(Fonts.RobotFieldText)
                                                            .foregroundColor(Color("greyishBrownThree"))
                                                            .bold()
                                                    }
                                                }else{
                                                    Text("")
                                                        .font(Fonts.RobotFieldText)
                                                        .foregroundColor(Color("greyishBrownThree"))
                                                        .bold()
                                                }
                                                Spacer()
                                            }
                                            HStack{
                                                Text("Email").font(Fonts.RobotRegular)
                                                    .foregroundColor(Color("brownishGrey"))
                                                Spacer()
                                            }.padding(.top)
                                            HStack{
                                                if(self.getPersonDetailService.fetchComplete == true){
                                                    if(self.getPersonDetailService.othersAddressBook.email1 != nil){
                                                        Text(self.getPersonDetailService.othersAddressBook.email1!)
                                                            .font(Fonts.RobotFieldText)
                                                            .foregroundColor(Color("greyishBrownThree"))
                                                            .bold()
                                                    }else{
                                                        Text("")
                                                            .font(Fonts.RobotFieldText)
                                                            .foregroundColor(Color("greyishBrownThree"))
                                                            .bold()
                                                    }
                                                }else{
                                                    Text("")
                                                        .font(Fonts.RobotFieldText)
                                                        .foregroundColor(Color("greyishBrownThree"))
                                                        .bold()
                                                }
                                                
                                                Spacer()
                                            }
                                        }.frame(height:100, alignment: .top)
                                    }.background(Color("whiteThree"))
                                        .foregroundColor(Color("whiteThree"))
                                    
                                }.padding()
                                
                                if(self.getPersonDetailService.fetchComplete == true && self.getPersonDetailService.peopleResponse.commonInterest!.count > 0){
                                    VStack{
                                        VStack{
                                            HStack{
                                                Text("Common Interests")
                                                    .foregroundColor(Color.white)
                                                    .bold()
                                                    .font(Fonts.RobotTitle)
                                                Spacer()
                                            }.padding()
                                                .background(Color("aquaMarine"))
                                            
                                            VStack{
                                                ForEach(self.getPersonDetailService.peopleResponse.commonInterest!){
                                                    interestMap in
                                                    VStack{
                                                        HStack{
                                                            Text(interestMap.interestCategory!.name!)
                                                                .foregroundColor(Color("aquaMarine"))
                                                                .bold()
                                                                .font(Fonts.RobotTitle)
                                                            Spacer()
                                                        }
                                                        ForEach(interestMap.interestList!){
                                                            interest in
                                                            HStack{
                                                                Text(interest.interestName!)
                                                                    .foregroundColor(Color("brownGrey"))
                                                                    .bold()
                                                                    .font(Fonts.RobotRegular)
                                                                Spacer()
                                                            }.padding(.vertical)
                                                            Divider()
                                                        }
                                                    }
                                                }
                                            }.padding()
                                        }.overlay(
                                            Rectangle()
                                                .stroke(Color("veryLightPink"), lineWidth: 1)
                                        )
                                    }.padding()
                                }
                                
                                if(self.getPersonDetailService.fetchComplete == true && self.getPersonDetailService.peopleResponse.unCommonInterest!.count > 0){
                                    VStack{
                                        VStack{
                                            HStack{
                                                Text("Other Interests")
                                                    .foregroundColor(Color.white)
                                                    .bold()
                                                    .font(Fonts.RobotTitle)
                                                Spacer()
                                            }.padding()
                                                .background(Color("whiteThree"))
                                            VStack{
                                                ForEach(self.getPersonDetailService.peopleResponse.unCommonInterest!){
                                                    interestMap in
                                                    VStack{
                                                        HStack{
                                                            Text(interestMap.interestCategory!.name!)
                                                                .foregroundColor(Color("brownishGrey"))
                                                                .bold()
                                                                .font(Fonts.RobotTitle)
                                                            Spacer()
                                                        }
                                                        ForEach(interestMap.interestList!){
                                                            interest in
                                                            HStack{
                                                                Text(interest.interestName!)
                                                                    .foregroundColor(Color("brownGrey"))
                                                                    .bold()
                                                                    .font(Fonts.RobotRegular)
                                                                Spacer()
                                                            }.padding(.vertical)
                                                            Divider()
                                                        }
                                                    }
                                                }
                                            }.padding()
                                        }.overlay(
                                            Rectangle()
                                                .stroke(Color("veryLightPink"), lineWidth: 1)
                                        )
                                    }.padding()
                                }
                                
                            }
                            if(self.getPersonDetailService.fetchComplete != true){
                                ActivityIndicator(shouldAnimate: .constant(true))
                            }
                            if(self.showAddFavouriteActivitiIndicater == true){
                                ActivityIndicator(shouldAnimate: .constant(true))
                            }
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

struct PeopleDetailPage_Previews: PreviewProvider {
    static var previews: some View {
        PeopleDetailPage(viewRouter: ViewRouter())
    }
}
