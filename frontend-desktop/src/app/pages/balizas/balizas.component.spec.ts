import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BalizasComponent } from './balizas.component';

describe('BalizasComponent', () => {
  let component: BalizasComponent;
  let fixture: ComponentFixture<BalizasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BalizasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BalizasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
