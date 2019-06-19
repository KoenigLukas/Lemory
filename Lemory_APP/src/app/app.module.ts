import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptModule } from "nativescript-angular/nativescript.module";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import {LoginComponent} from "~/app/login/login.component";
import {RegisterComponent} from "~/app/register/register.component";

@NgModule({
    bootstrap: [
        AppComponent
    ],
    imports: [
        NativeScriptModule,
        AppRoutingModule,

    ],
    declarations: [
        AppComponent,
        LoginComponent,
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
export class AppModule { }
