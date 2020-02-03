//
//  PeopleListPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct PeopleListPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var getPeopleListService:GetPeopleListService
    
    @State var peopleSearchText:String = ""
    @State var peopleSearchCancel:Bool = false
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.getPeopleListService = GetPeopleListService(viewRouter: viewRouter)
        UITableView.appearance().tableFooterView = UIView()
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.COMMUNIT_LANDING_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "PEOPLE")
                        Spacer()
                    }.padding()
                    VStack{
                        HStack {
                            HStack {
                                TextField("search", text: self.$peopleSearchText, onEditingChanged: { isEditing in
                                    self.peopleSearchCancel = true
                                }, onCommit: {
                                    print("onCommit")
                                }).foregroundColor(.primary)
                                
                                Button(action: {
                                    self.peopleSearchText = ""
                                }) {
                                    Image(systemName: "xmark.circle.fill").opacity(self.peopleSearchText == "" ? 0 : 1)
                                }
                                Button(action: {
                                    
                                }) {
                                    Image(systemName: "magnifyingglass")
                                }.padding(.leading)
                                
                                
                            }
                            .padding(EdgeInsets(top: 8, leading: 6, bottom: 8, trailing: 15))
                            .foregroundColor(.secondary)
                            .background(Color.white)
                            .cornerRadius(20.0)
                            .overlay(
                                RoundedRectangle(cornerRadius:20)
                                    .stroke(Color("veryLightPink"), lineWidth: 1)
                            )
                        }
                        .padding(.horizontal)
                        .navigationBarHidden(self.peopleSearchCancel)
                    }
                    ZStack{
                        VStack{
                            if(self.getPeopleListService.fetchComplete == true && self.getPeopleListService.peopleResponse.peopleList != nil){
                                List {
                                    ForEach(self.getPeopleListService.peopleResponse.personList!){ person in
                                        Button(action:{
                                            self.viewRouter.primaryKey = person.addressBook!.userId!
                                            self.viewRouter.currentPage = ViewRoutes.PEOPLE_DETAIL_PAGE
                                        }){
                                            PeopleCard(
                                                addressBook: person.addressBook!,
                                                peopleResponse: self.getPeopleListService.peopleResponse,
                                                person: person)
                                        }
                                    }
                                    
                                    
                                }
                            }
                        }
                        if(self.getPeopleListService.fetchComplete != true){
                            ActivityIndicator(shouldAnimate: .constant(true))
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

struct PeopleCard : View {
    @State var progressBarValue:CGFloat = 0.55
    @State var addressBook: AddressBook
    @State var peopleResponse:PeopleResponse
    @State var person:Person
    var body: some View {
        VStack{
            VStack{
                HStack{
                    VStack{
                        Image(systemName: "person.fill")
                            .resizable()
                            .clipShape(Circle())
                            .shadow(radius: 5)
                    } .frame(width:40, height:40)
                    
                    VStack{
                        HStack{
                            Text(self.addressBook.displayName!)
                                .font(Fonts.RobotRegularButton)
                                .bold()
                                .foregroundColor(Color("greyishBrownFour"))
                            Spacer()
                        }
                        HStack{
                            Text("Male")
                                .font(Fonts.RobotRegularSmallText)
                                .foregroundColor(Color("brownishGrey"))
                            Spacer()
                        }
                    }
                    Spacer()
                    VStack{
                        Circle()
                            .frame(width:8,height: 8)
                            .foregroundColor(Color("pastelRed"))
                    }.frame(alignment:.top)
                }.padding()
                VStack{
                    HStack{
                        if(person.isFavourite == 1){
                            GuestJiniRoundButtonSystemImage(systemImage: "heart.fill")
                        }else{
                            GuestJiniRoundButtonSystemImage(systemImage: "heart")
                        }
                        ProgressBar(
                            value:0,
                            totalCount: self.peopleResponse.totalInterestCount!,
                            currentCount: self.person.userInterestsList!.count
                        )
                        Spacer()
                    }
                }.padding()
                    .frame(height: 70)
                
            }.background(Color("whiteThree"))
                .cornerRadius(10)
                .padding()
        }
        
    }
}

struct ProgressBar:View{
    @State var value:CGFloat = 0.0
    var totalCount: Int
    var currentCount: Int
    
    func getProgressBarWidth(geometry:GeometryProxy) -> CGFloat {
        var width:CGFloat = 0
        if(currentCount > totalCount){
            width = 1;
        }else if(self.totalCount == 0){
            width = 0
        }else{
            width = CGFloat(self.currentCount)/CGFloat(self.totalCount);
        }
        let frame = geometry.frame(in: .global)
        return frame.size.width *  width
    }
    
    func getPercentage(_ value:CGFloat) -> String {
        let intValue = Int(ceil(value * 100))
        return "\(intValue) %"
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack(alignment: .leading) {
                ZStack(alignment: .leading) {
                    Rectangle()
                        .opacity(0)
                        .overlay(
                            Rectangle()
                                .stroke(Color("aquaMarine"), lineWidth: 1)
                    )
                    Rectangle()
                        .frame(minWidth: 0, idealWidth:self.getProgressBarWidth(geometry: geometry),
                               maxWidth: self.getProgressBarWidth(geometry: geometry))
                        .foregroundColor(Color("aquaMarine"))
                        .animation(.default)
                }
                .frame(height:10)
                HStack{
                    Text("Common Interests")
                    Spacer();
                    Text( "\(self.currentCount)/\(self.totalCount)")
                        .font(Fonts.RobotRegular)
                }
                
            }.frame(height:10)
        }
    }
    
}
struct PeopleListPage_Previews: PreviewProvider {
    static var previews: some View {
        PeopleListPage(viewRouter: ViewRouter())
    }
}
