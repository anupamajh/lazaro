//
//  PaymentView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 04/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct PaymentView: View {
    @ObservedObject var viewRouter: ViewRouter
    var request:URLRequest
    let webView:WebView
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter
        let url = URL(string: "http://139.59.50.103:8012/pgredirect")!
        self.request = URLRequest(url: url)
        request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        request.httpMethod = "POST"
        let parameters: [String: Any] = [
            "CUST_ID": viewRouter.primaryKey,
            "EMAIL": "prasanna.pete@gmail.com",
            "TXN_AMOUNT": viewRouter.transactionAMount
        ]
        request.httpBody = parameters.percentEncoded()
        webView = WebView(request: request)
        
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.RENT_INVOICE_LIST_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "RENT INVOICE")
                        Spacer()
                    }.padding()
                    self.webView
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct PaymentView_Previews: PreviewProvider {
    static var previews: some View {
        PaymentView(viewRouter: ViewRouter())
    }
}
