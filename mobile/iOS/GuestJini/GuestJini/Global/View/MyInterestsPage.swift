//
//  MyInterestsPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct MyInterestsPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var getInterestCategoryListService:GetInterestCategoryListService
    @ObservedObject var getInterestListService:GetInterestListService
    @ObservedObject var getUserInterests:GetUserInterests
    @ObservedObject var saveUserInterests:SaveUserInterests
    
    @State private var activeItemCategory: String = ""

    
    init(viewRouter:ViewRouter) {
        self.viewRouter = viewRouter
        self.getInterestCategoryListService = GetInterestCategoryListService(viewRouter: viewRouter)
        self.getInterestListService = GetInterestListService(viewRouter: viewRouter)
        self.getUserInterests = GetUserInterests(viewRouter: viewRouter)
        self.saveUserInterests = SaveUserInterests(viewRouter: viewRouter)
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.MY_PROFILE_VIEW
                            }) {
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                
                            }.padding(.horizontal)
                            
                            GuestJiniTitleText(title: "MY INTERESTS")
                            Spacer()
                        }.padding()
                        
                    ZStack{
                        if(self.getInterestListService.fetchComplete == false &&
                            self.getInterestCategoryListService.fetchComplete == false &&
                            self.getUserInterests.fetchComplete == false
                            ){
                            ActivityIndicator(shouldAnimate: .constant(true))
                        }else{
                            List{
                                ForEach(self.getInterestCategoryListService.interestCategoryList){ category in
                                    Button(action:{
                                        self.headerTapAction(for: category.id!)
                                    }){
                                        HeaderRowView(category: category)
                                    }.listRowBackground(Color(UIColor.secondarySystemBackground))

                                    if (category.id == self.activeItemCategory) {
                                        ForEach(self
                                            .getInterestListService
                                            .interestList.filter{($0.interestCategoryId == category.id)}
                                        ) { interest in
                                            Button(action: { }) {
                                                InterestRowView(
                                                    interest: interest,
                                                    saveUserInterests: self.saveUserInterests,
                                                    checkState:  (self.getUserInterests.userInterestList
                                                        .filter{($0.interestId == interest.id && $0.isInterested == 1)}.count > 0 ) ? true : false
                                                )
                                                .padding()
                                            }
                                        }
                                    }
                                }
                                
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
    
    func headerTapAction(for categoriId: String) {
        if categoriId == self.activeItemCategory {
            self.activeItemCategory = ""
        } else {
            self.activeItemCategory = categoriId
        }
    }
    
}

struct InterestRowView: View {
    @State var interest: Interest
    @ObservedObject var saveUserInterests:SaveUserInterests
    @State var checkState:Bool = false ;
    
    
    var body: some View {
        VStack{
            Button(action:
                {
                    self.checkState = !self.checkState
                    let userInterests:UserInterests = UserInterests()
                    userInterests.interestId = self.interest.id
                    userInterests.isInterested = self.checkState ? 1 : 0
                    self.saveUserInterests.saveUserInterest(userInterest: userInterests) { (response) in
                        print("Saved")
                    }
                    
            }) {
                HStack(alignment: .top, spacing: 10) {
                    Text(interest.name!)
                        .foregroundColor(Color("brownishGrey"))
                        .font(Fonts.RobotRegular)
                    Spacer()
                    
                     Image(systemName: (self.checkState ? "checkmark.square" : "square"))
                        .frame(width:20, height:20, alignment: .center)
                        .cornerRadius(5)
                }
            }
        }
        .foregroundColor(.primary)
    }
}


struct HeaderRowView: View {
    @State var category: InterestCategory
    var body: some View {
        HStack{
            Text(category.name!)
                .font(.headline)
                .foregroundColor(Color(UIColor.secondaryLabel))
            Spacer()
            
            VStack{
                Image(systemName: "chevron.down")
                    .resizable()
                    .foregroundColor(Color("greyishBrownFour"))
            }.frame(width: 8, height: 5, alignment: .center)
        }.padding()
    }
}

struct MyInterestsPage_Previews: PreviewProvider {
    static var previews: some View {
        MyInterestsPage(viewRouter: ViewRouter())
    }
}
