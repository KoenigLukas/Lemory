import {Component, OnInit} from "@angular/core";

@Component({
    selector: "Register",
    moduleId: module.id,
    templateUrl: "./register.component.html"
})
export class RegisterComponent implements OnInit {

    constructor() {
        // Use the component constructor to inject providers.
    }

    ngOnInit(): void {
        // Init your component properties here.
    }

    onButtonTap(): void {
        console.log("Button was pressed");
    }

    progressValue: number = 80;
    currentDay: number = new Date().getDate();
}
