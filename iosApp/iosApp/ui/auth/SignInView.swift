//
//  SignInView.swift
//  iosApp
//
//  Created by Dostonbek Kamalov on 12/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import BetterSafariView

private let REDIRECT_URI = "redirect_uri"
private let CLIENT_ID = "client_id"
private let SCOPE = "user,repo,gist,notifications,read:org"
private let STATE = "state"
private let CALLBACK_URL_SCHEME = "callback_url_scheme"

struct SignInView : View {
    
    @State private var startingWebAuthenticationSession = false
    
    var body: some View {
        Button(action: {
            self.startingWebAuthenticationSession = true
        }) {
            Text("OAuth via Github")
                .padding()
                .foregroundColor(.white)
                .background(Color.blue)
                .cornerRadius(8)
        }
        .shadow(color: Color.blue, radius: 20, y: 5)
        .webAuthenticationSession(isPresented: $startingWebAuthenticationSession) {
            WebAuthenticationSession(
                url: URL(string: "https://github.com/login/oauth/authorize?client_id=\(CLIENT_ID)&redirect_uri=\(REDIRECT_URI)&scope=\(SCOPE)&state=\(STATE)")!,
                callbackURLScheme: CALLBACK_URL_SCHEME
            ) { (callbackURL: URL?, error: Error?) in
                print(callbackURL as Any, error as Any)
            }
            .prefersEphemeralWebBrowserSession(false)
        }
    }
}
