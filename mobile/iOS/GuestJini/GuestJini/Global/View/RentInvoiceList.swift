//
//  RentInvoiceList.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct RentInvoiceList: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var getMyRentInvoicesService:GetMyRentInvoicesService
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.getMyRentInvoicesService = GetMyRentInvoicesService(viewRouter: viewRouter)
        UITableView.appearance().tableFooterView = UIView()
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.ACCOUNTS_LANDING_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "RENT INVOICE")
                        Spacer()
                        
                    }.padding()
                    if(self.getMyRentInvoicesService.fetchComplete == true){
                        ScrollView{
                            if(self.getMyRentInvoicesService.accountTicketResponse.accountTicketList!.count == 0){
                                VStack{
                                    HStack{
                                        Text("EMPTY! There are no invoices yet.")
                                            .foregroundColor(Color("greyishBrownThree"))
                                            .font(Fonts.RobotRegular)
                                            .bold()
                                        Spacer()
                                        
                                    }.padding()
                                        .background(Color("whiteThree"))
                                }.padding()
                            }else{
                                ForEach(self.getMyRentInvoicesService.accountTicketResponse.accountTicketList!){ accountTicket in
                                    AccountTicketListRow(viewRouter: self.viewRouter, accountTicket: accountTicket)
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
}

struct AccountTicketListRow: View{
    @ObservedObject var viewRouter: ViewRouter
    var accountTicket:AccountTicket
    
    var body: some View {
        VStack{
            HStack{
                VStack{
                    Text(accountTicket.ticketDate!.convetToDateFromMySQLUTC())
                        .font(Fonts.RobotRegular)
                        .bold()
                        .foregroundColor(Color("greyishBrownFour"))
                    Text(accountTicket.ticketNumber!)
                        .font(Fonts.RobotRegularSmallText)
                        .foregroundColor(Color("brownishGrey"))
                }
                Spacer()
                VStack{
                    Text( "Rs. \(accountTicket.netTotal!)")
                    .font(Fonts.RobotRegular)
                    .bold()
                    .foregroundColor(Color("greyishBrownFour"))
                }
                Spacer()
                Button(action:{
                    self.viewRouter.primaryKey = self.accountTicket.id!
                    self.viewRouter.currentPage = ViewRoutes.RENT_INVOICE_DETAIL_PAGE
                }){
                    GuestJiniRoundButtonSystemImage(systemImage: "arrow.right")
                }
            }.padding(.horizontal)
            Divider()
        }
    }
}

struct RentInvoiceList_Previews: PreviewProvider {
    static var previews: some View {
        RentInvoiceList(viewRouter: ViewRouter())
    }
}
