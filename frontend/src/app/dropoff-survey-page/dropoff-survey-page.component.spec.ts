import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DropoffSurveyPageComponent } from './dropoff-survey-page.component';

describe('DropoffSurveyPageComponent', () => {
  let component: DropoffSurveyPageComponent;
  let fixture: ComponentFixture<DropoffSurveyPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DropoffSurveyPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DropoffSurveyPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
