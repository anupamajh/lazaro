//
//  AccountsLandingPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct AccountsLandingPage: View {
    @ObservedObject var viewRouter: ViewRouter
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    VStack{
                        Button(action:{
                            self.viewRouter.currentPage = ViewRoutes.RENT_INVOICE_LIST_PAGE
                        })
                        {
                            AccountsLandingPageCard(title: "RENT INVOICE", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis")
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            
                        })
                        {
                            AccountsLandingPageCard(title: "RECEIPTS", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis")
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            
                        })
                        {
                            AccountsLandingPageCard(title: "LEDGER", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis")
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            
                        })
                        {
                            AccountsLandingPageCard(title: "BILLS", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis")
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


struct AccountsLandingPageCard: View {
    var title:String = ""
    var description:String = ""
    
    var body: some View {
        VStack{
            VStack{
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

struct AccountsLandingPage_Previews: PreviewProvider {
    static var previews: some View {
        AccountsLandingPage(viewRouter: ViewRouter())
    }
}
